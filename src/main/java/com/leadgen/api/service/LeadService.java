package com.leadgen.api.service;

import com.leadgen.api.entity.Lead;

import java.util.List;
import java.util.Optional;

public interface LeadService {
    List<Lead> getAllLeads();

    Optional<Lead> getLeadById(Long id);

    Lead saveLead(Lead lead);

    void deleteLead(Long id);

    String createLead(Long itemId, Long leadId);
}
