package com.projetkafka.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.projetkafka.domain.DemandeEvent;
import com.projetkafka.producer.DemandeEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
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

        //invoke kafka producer
        log.info("before sendDemandeEvent");
        // demandeEventProducer.sendDemandeEvent(demandeEvent);
        SendResult<Integer, String> sendResult = demandeEventProducer.sendDemandeEventSynchronous(demandeEvent);
        log.info("SendResult is {} ", sendResult.toString());
        log.info("after sendLibraryEvent");
        return ResponseEntity.status(HttpStatus.CREATED).body(demandeEvent);
    }
}
