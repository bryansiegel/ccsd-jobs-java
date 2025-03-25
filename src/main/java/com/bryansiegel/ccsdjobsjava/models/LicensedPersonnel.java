package com.bryansiegel.ccsdjobsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LicensedPersonnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    //job code
    //division
    //classification
    //terms of employment
    //FLSA status

    //position summary
    //essential duties and responsibilities
    //position expectations

    //position requirements // don't create
    //education and training
    //licenses and certifications
    //preferred qualifications

    //Job Revision Information // don't create
    //revised
    //created
}
