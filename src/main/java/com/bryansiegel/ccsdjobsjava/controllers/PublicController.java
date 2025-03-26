package com.bryansiegel.ccsdjobsjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PublicController {

    @GetMapping("/administrative-personnel")
    public String getAllAdministrativePersonnel() {
        return "/public/AdministrativePersonnel.html";
    }
    @GetMapping("/administrative-personnel/{id}")
    public String getAllAdministrativePersonnel(@PathVariable("id") String id) {
        return "/public/AdministrativePersonnelView.html";
    }
}

