package com.bryansiegel.ccsdjobsjava.controllers;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.AdministrativePersonnelRepo;
import com.bryansiegel.ccsdjobsjava.services.ExportExcelService;
import com.bryansiegel.ccsdjobsjava.services.ExportPDFService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
public class PublicController {

    @Autowired
    private AdministrativePersonnelRepo administrativePersonnelRepo;

    @Autowired
    private ExportExcelService excelExportService;
    //
    @Autowired
    private ExportPDFService exportPDFService;

    @GetMapping("/")
    public String index() {
        return "/public/index.html";
    }

    @GetMapping("/administrative-personnel")
    public String getAllAdministrativePersonnel() {
        return "/public/AdministrativePersonnel.html";
    }

    @GetMapping("/administrative-personnel/{id}")
    public String getAllAdministrativePersonnel(@PathVariable("id") Long id, Model model) {
        Optional<AdministrativePersonnel> personnel = administrativePersonnelRepo.findById(id);
        if (personnel.isPresent()) {
            model.addAttribute("administrativePersonnel", personnel.get());
            return "/public/AdministrativePersonnelView.html";
        } else {
            return "error/404";
        }
    }

    //Export Excel
    @GetMapping("/administrative-personnel/export/excel")
    @ResponseBody
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=administrative_personnel.xlsx");
        List<AdministrativePersonnel> personnelList = administrativePersonnelRepo.findAll();
        ByteArrayInputStream stream = excelExportService.exportToExcel(personnelList);
        org.apache.commons.io.IOUtils.copy(stream, response.getOutputStream());
    }

    // Export PDF
    @GetMapping("/administrative-personnel/{id}/export/pdf")
    @ResponseBody
    public void exportToPdfById(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=administrative_personnel_" + id + ".pdf");
        Optional<AdministrativePersonnel> personnel = administrativePersonnelRepo.findById(id);
        if (personnel.isPresent()) {
            ByteArrayInputStream stream = exportPDFService.exportToPdf(personnel.get());
            org.apache.commons.io.IOUtils.copy(stream, response.getOutputStream());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Administrative Personnel not found");
        }
    }
}


