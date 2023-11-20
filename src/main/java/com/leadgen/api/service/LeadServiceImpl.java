package com.leadgen.api.service;

import com.leadgen.api.constants.ApplicationConstants;
import com.leadgen.api.entity.Item;
import com.leadgen.api.entity.Lead;
import com.leadgen.api.repository.ItemRepository;
import com.leadgen.api.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.leadgen.api.constants.ApplicationConstants.*;

@Service
public class LeadServiceImpl implements LeadService {
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Lead> getAllLeads() {
        List<Lead> allLeadsList = leadRepository.findAll();

        if(!allLeadsList.isEmpty()) {
            return leadRepository.findAll();
        }
        else {
            return new ArrayList<Lead>();
        }
    }

    @Override
    public Optional<Lead> getLeadById(Long id) {
        return leadRepository.findById(id);
    }

    @Override
    public Lead saveLead(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
        /* TODO: Check if lead exists, before deleting.
        Lead leadInRepo = leadRepository.findLeadById(id);

        // Check if the ID is legal - points to element in DB before attempting operation.
        // TODO: Consider using optional? Look at create lead.
        if(leadInRepo != null) {
            leadRepository.deleteById(id);
        }
        else {

        }*/

    }

    @Override
    public String createLead(Long itemId, Long leadId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        Optional<Lead> optionalLead = leadRepository.findLeadById(leadId);

        if (optionalItem.isPresent() && optionalLead.isPresent()) {
            Item item = optionalItem.get();
            Lead lead = optionalLead.get();

            lead.setItem(item);

            leadRepository.save(lead);
            // TODO: Define constant file - don't hard code values.
            return LEAD_SERVICE_CREATE_SUCCESS;
        } else {
            if (!optionalLead.isPresent()) {
                return LEAD_SERVICE_LEAD_NOT_FOUND;
            } else if (optionalItem.isPresent()) {
                return LEAD_SERVICE_ITEM_NOT_FOUND;
            }

        }
        // TODO: default return value?
        return "";
    }
}