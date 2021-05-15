package com.projetkafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data//generate actual getters and setters and tostring
@Builder
public class DemandeEvent {

    private Integer demandeEventId;
    private Aide aide;

}
