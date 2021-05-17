package com.projetkafka.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Aide {

    private Integer aideId;
    private String format;
    private String numEtudiant;
    private String typeAide;
    private String description;
}
