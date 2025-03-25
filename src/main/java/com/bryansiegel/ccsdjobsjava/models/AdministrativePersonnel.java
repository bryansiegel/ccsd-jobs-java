package com.bryansiegel.ccsdjobsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AdministrativePersonnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //job code
    //reference code
    //division_unit
    //classification
    //terms of employment
    //FLSA status

    //position summary
    //essential duties and responsibilities
    //position expectations

    //position requirements// don't create
    //education and training
    //licenses and certifications
    //experience

    //Job Revision Information// don't create
    //revised
    //created


}
