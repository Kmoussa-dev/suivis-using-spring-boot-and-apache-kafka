package com.projetkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetkafka.entity.DemandeEvent;
import com.projetkafka.jpa.DemandeEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DemandeEventsService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private DemandeEventsRepository demandeEventsRepository;

    public void processDemandeEvent(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        
        DemandeEvent demandeEvent = objectMapper.readValue(consumerRecord.value(), DemandeEvent.class);
        log.info("demandeEvent : {} ", demandeEvent);

        switch(demandeEvent.getDemandeEventType()){
            case NEW:
                save(demandeEvent);
                break;
            case UPDATE:
                //validate the demandeevent
                validate(demandeEvent);
                save(demandeEvent);
                break;
            default:
                log.info("Invalid Demande Event Type");
        }

    }

    private void validate(DemandeEvent demandeEvent) {
        if(demandeEvent.getDemandeEventId()==null){
            throw new IllegalArgumentException("Demande Event Id is missing");
        }

        Optional<DemandeEvent> demandeEventOptional = demandeEventsRepository.findById(demandeEvent.getDemandeEventId());
        if(!demandeEventOptional.isPresent()){
            throw new IllegalArgumentException("Not a valid library Event");
        }
        log.info("Validation is successful for the demande Event : {} ", demandeEventOptional.get());
    }


    private void save(DemandeEvent demandeEvent) {
        demandeEvent.getAide().setDemandeEvent(demandeEvent);
        demandeEventsRepository.save(demandeEvent);
        log.info("Successfully Persisted the demande Event {} ", demandeEvent);
    }
}
