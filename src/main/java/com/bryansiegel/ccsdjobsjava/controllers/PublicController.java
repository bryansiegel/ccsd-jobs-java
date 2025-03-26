package com.bryansiegel.ccsdjobsjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @GetMapping("/administrative-personnel")
    public String getAllAdministrativePersonnel() {
        return "/public/AdministrativePersonnel.html";
    }
}

