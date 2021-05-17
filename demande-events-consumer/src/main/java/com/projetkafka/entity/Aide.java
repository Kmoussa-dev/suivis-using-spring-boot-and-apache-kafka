package com.projetkafka.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Aide {

    @Id
    private Integer aideId;
    private String format;
    private String numEtudiant;
    private String Organisme;
    private String typeAide;
    private String description;

    @OneToOne
    @JoinColumn(name = "demandeEventId")
    private DemandeEvent demandeEvent;
}
