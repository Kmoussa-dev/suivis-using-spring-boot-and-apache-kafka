package com.projetkafka.jpa;

import com.projetkafka.entity.DemandeEvent;
import org.springframework.data.repository.CrudRepository;

public interface DemandeEventsRepository extends CrudRepository<DemandeEvent,Integer> {
}
