package com.projetkafka.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.projetkafka.domain.DemandeEvent;
import com.projetkafka.domain.DemandeEventType;
import com.projetkafka.producer.DemandeEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
public class DemandeEventsController {

    @Autowired
    DemandeEventProducer demandeEventProducer;

    @PostMapping("/v1/demandeevent")
    public ResponseEntity<DemandeEvent> postDemandeEvent(@RequestBody DemandeEvent demandeEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        demandeEvent.setDemandeEventType(DemandeEventType.NEW);
        demandeEventProducer.sendDemandeEvent_Approach2(demandeEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(demandeEvent);
    }

    @PutMapping("/v1/demandeevent")
    public ResponseEntity<?> putDemandeEvent(@RequestBody DemandeEvent demandeEvent) throws JsonProcessingException{

        if(demandeEvent.getDemandeEventId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass the DemandeEventId");
        }

        demandeEvent.setDemandeEventType(DemandeEventType.UPDATE);
        demandeEventProducer.sendDemandeEvent_Approach2(demandeEvent);
        return ResponseEntity.status(HttpStatus.OK).body(demandeEvent);
    }
}
