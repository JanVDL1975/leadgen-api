package com.leadgen.api.controller;

import com.leadgen.api.entity.Lead;
import com.leadgen.api.service.LeadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.List;

import static com.leadgen.api.constants.ApplicationConstants.*;

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
    public ResponseEntity<String> deleteLead(@PathVariable("id") Long id) {
        try {
            Optional<Lead> optionalValReturned = leadServiceImpl.getLeadById(id);

            // Available to delete in first place
            if(optionalValReturned.isPresent()) {
                // Delete
                leadServiceImpl.deleteLead(id);

                // Verify it was deleted
                optionalValReturned = leadServiceImpl.getLeadById(id);
                if(optionalValReturned.isEmpty()) {
                    return new ResponseEntity<>(LEAD_CONTROLLER_DELETE_SUCCESS, HttpStatus.OK);
                }
            }
            else {
                return new ResponseEntity<>(LEAD_CONTROLLER_NO_LEAD_TO_DELETE, HttpStatus.BAD_REQUEST);
            }

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(LEAD_CONTROLLER_NO_LEAD_TO_DELETE, HttpStatus.BAD_REQUEST);
        } catch (Exception exc) {
            return new ResponseEntity<>(LEAD_CONTROLLER_ERROR + exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(LEAD_CONTROLLER_DELETE_SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createLead(@RequestBody Map<String, Object> payload) {
        try {
            Long itemId = Long.valueOf(payload.get("itemId").toString());
            Long leadId = Long.valueOf(payload.get("leadId").toString());

            String result = leadServiceImpl.createLead(itemId, leadId);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (NumberFormatException nfe) {
            return new ResponseEntity<>(LEAD_CONTROLLER_FORMATTING_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception exc) {
            return new ResponseEntity<>(LEAD_CONTROLLER_ERROR + exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}