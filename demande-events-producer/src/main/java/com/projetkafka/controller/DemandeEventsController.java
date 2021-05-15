package com.projetkafka.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.projetkafka.domain.DemandeEvent;
import com.projetkafka.producer.DemandeEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemandeEventsController {

    @Autowired
    DemandeEventProducer demandeEventProducer;

    @PostMapping("/v1/demandeevent")
    public ResponseEntity<DemandeEvent> postDemandeEvent(@RequestBody DemandeEvent demandeEvent) throws JsonProcessingException {

        //invoke kafka producer
        demandeEventProducer.sendDemandeEvent(demandeEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(demandeEvent);
    }
}
