package com.projetkafka.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class DemandeEvent {

    @Id
    @GeneratedValue
    private Integer demandeEventId;
    @Enumerated(EnumType.STRING)
    private DemandeEventType demandeEventType;
    @OneToOne(mappedBy = "demandeEvent", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private Aide aide;

}
