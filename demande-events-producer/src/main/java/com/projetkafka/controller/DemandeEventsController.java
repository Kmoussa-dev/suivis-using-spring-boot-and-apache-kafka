package com.projetkafka.controller;


import com.projetkafka.domain.DemandeEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemandeEventsController {

    @PostMapping("/v1/demandeevent")
    public ResponseEntity<DemandeEvent> postDemandeEvent(@RequestBody DemandeEvent demandeEvent){

        //invoke kafka producer
        return ResponseEntity.status(HttpStatus.CREATED).body(demandeEvent);
    }
}
