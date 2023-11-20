package com.leadgen.api.controller;

import com.leadgen.api.entity.Lead;
import com.leadgen.api.service.LeadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.List;

import static com.leadgen.api.constants.ApplicationConstants.LEAD_CONTROLLER_ERROR;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
    @Autowired
    private LeadServiceImpl leadServiceImpl;

    @GetMapping
    public List<Lead> getAllLeads() {
        return leadServiceImpl.getAllLeads();
    }

    @GetMapping("/{id}")
    public Optional<Lead> getLeadById(@PathVariable("id") Long id) {
        return leadServiceImpl.getLeadById(id);
    }

    @PostMapping
    public Lead saveLead(@RequestBody Lead lead) {
        return leadServiceImpl.saveLead(lead);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable("id") Long id) {
        leadServiceImpl.deleteLead(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createLead(@RequestBody Map<String, Object> payload) {
        try {
            Long itemId = Long.valueOf(payload.get("itemId").toString());
            Long leadId = Long.valueOf(payload.get("leadId").toString());

            String result = leadServiceImpl.createLead(itemId, leadId);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid itemId or leadId format", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(LEAD_CONTROLLER_ERROR  + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}