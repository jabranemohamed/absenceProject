package com.absence.calendar.entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private UserEntity user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_type")
    private AbsenceType type;

    @NonNull
    private Date date_debut;

    @NonNull
    private Date date_fin;

    @NonNull
    private boolean valider;




}
