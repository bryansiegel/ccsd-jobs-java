package com.bryansiegel.ccsdjobsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SupportProfessional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    //class code
    //job family
    //classification
    //terms of employment
    //FLSA status

    //position summary
    //essential duties and responsibilities
    //distinguishing characteristics
    //Knowledge, Skills, and Abilities

    //position requirements // don't create
    //education and training
    //licenses and certifications
    //preferred qualifications

    //Docment(s) required at time of application

    //examples of assigned work areas

    //work environment

    //examples of equipment/supplies used to perform tasks

    //Job Revision Information // don't create
    //revised
    //created
}
