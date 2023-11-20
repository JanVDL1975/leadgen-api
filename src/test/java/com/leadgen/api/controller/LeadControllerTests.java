package com.leadgen.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadgen.api.entity.Item;
import com.leadgen.api.entity.Lead;
import com.leadgen.api.service.LeadServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static com.leadgen.api.constants.ApplicationConstants.LEAD_CONTROLLER_DELETE_SUCCESS;
import static com.leadgen.api.constants.ApplicationConstants.LEAD_CONTROLLER_NO_LEAD_TO_DELETE;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LeadController.class)
public class LeadControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LeadServiceImpl leadService;

    private final Random random = new Random();

    private String randomString() {
        return Long.toHexString(random.nextLong());
    }

    private String randomEmail() {
        return randomString() + "@example.com";
    }

    @Test
    //TODO Fix this
    @Disabled("This test is currently disabled for a specific reason.")
    public void getAllLeads_ReturnsListOfLeads() throws Exception {
        // Arrange
        Lead lead1 = new Lead(random.nextLong(), randomString(), randomString(), randomEmail(), randomString(), randomString(), null);
        Lead lead2 = new Lead(random.nextLong(), randomString(), randomString(), randomEmail(), randomString(), randomString(), null);
        //TODO Check this out.
        //Lead lead3 = new Lead(random.nextLong(), randomString(), null, null, null, null, new Item(random.nextLong(), randomString(), randomString(), randomString(), randomString(), randomString(), "http://www.google.com/image.jpg", true));
        Lead lead4 = new Lead(random.nextLong(), randomString(), randomString(), randomEmail(), randomString(), randomString(), new Item(random.nextLong(), randomString(), randomString(), randomString(), randomString(), randomString(), "http://www.google.com/image.jpg", true));
        Lead lead5 = new Lead(random.nextLong(), randomString(), randomString(), randomEmail(), randomString(), randomString(), new Item(random.nextLong(), randomString(), randomString(), randomString(), randomString(), randomString(), "http://www.google.com/image.jpg", true));

        List<Lead> leads = Arrays.asList(lead1, lead2, lead4, lead5);
        when(leadService.getAllLeads()).thenReturn(leads);

        // Act & Assert
        mockMvc.perform(get("/api/leads"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())

                .andExpect(jsonPath("$[0].id").value(lead1.getId()))
                .andExpect(jsonPath("$[0].firstName").value(lead1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(lead1.getLastName()))
                .andExpect(jsonPath("$[0].email").value(lead1.getEmail()))
                .andExpect(jsonPath("$[0].phone").value(lead1.getPhone()))
                .andExpect(jsonPath("$[0].message").value(lead1.getMessage()))
                .andExpect(jsonPath("$[0].item").doesNotExist())

                .andExpect(jsonPath("$[1].id").value(lead2.getId()))
                .andExpect(jsonPath("$[1].firstName").value(lead2.getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(lead2.getLastName()))
                .andExpect(jsonPath("$[1].email").value(lead2.getEmail()))
                .andExpect(jsonPath("$[1].phone").value(lead2.getPhone()))
                .andExpect(jsonPath("$[1].message").value(lead2.getMessage()))
                .andExpect(jsonPath("$[1].item").doesNotExist())

         /*       .andExpect(jsonPath("$[2].id").value(lead3.getId()))
                .andExpect(jsonPath("$[2].firstName").value(lead3.getFirstName()))
                .andExpect(jsonPath("$[2].lastName").value(null))
                .andExpect(jsonPath("$[2].email").value(null))
                .andExpect(jsonPath("$[2].phone").value(null))
                .andExpect(jsonPath("$[2].message", nullValue()))
                .andExpect(jsonPath("$[2].item.id").value(lead3.getItem().getId()))
                .andExpect(jsonPath("$[2].item.name").value(lead3.getItem().getName()))
                .andExpect(jsonPath("$[2].item.description").value(lead3.getItem().getDescription()))
                .andExpect(jsonPath("$[2].item.category").value(lead3.getItem().getCategory()))
                .andExpect(jsonPath("$[2].item.brand").value(lead3.getItem().getBrand()))
                .andExpect(jsonPath("$[2].item.model").value(lead3.getItem().getModel()))
                .andExpect(jsonPath("$[2].item.image").value(lead3.getItem().getImage()))
                .andExpect(jsonPath("$[2].item.availability").value(lead3.getItem().isAvailability()))*/

                .andExpect(jsonPath("$[3].id").value(lead4.getId()))
                .andExpect(jsonPath("$[3].firstName").value(lead4.getFirstName()))
                .andExpect(jsonPath("$[3].lastName").value(lead4.getLastName()))
                .andExpect(jsonPath("$[3].email").value(lead4.getEmail()))
                .andExpect(jsonPath("$[3].phone").value(lead4.getPhone()))
                .andExpect(jsonPath("$[3].message").value(lead4.getMessage()))
                .andExpect(jsonPath("$[3].item.id").value(lead4.getItem().getId()))
                .andExpect(jsonPath("$[3].item.name").value(lead4.getItem().getName()))
                .andExpect(jsonPath("$[3].item.description").value(lead4.getItem().getDescription()))
                .andExpect(jsonPath("$[3].item.category").value(lead4.getItem().getCategory()))
                .andExpect(jsonPath("$[3].item.brand").value(lead4.getItem().getBrand()))
                .andExpect(jsonPath("$[3].item.model").value(lead4.getItem().getModel()))
                .andExpect(jsonPath("$[3].item.image").value(lead4.getItem().getImage()))
                .andExpect(jsonPath("$[3].item.availability").value(lead4.getItem().isAvailability()))

                .andExpect(jsonPath("$[4].id").value(lead5.getId()))
                .andExpect(jsonPath("$[4].firstName").value(lead5.getFirstName()))
                .andExpect(jsonPath("$[4].lastName").value(lead5.getLastName()))
                .andExpect(jsonPath("$[4].email").value(lead5.getEmail()))
                .andExpect(jsonPath("$[4].phone").value(lead5.getPhone()))
                .andExpect(jsonPath("$[4].message").value(lead5.getMessage()))
                .andExpect(jsonPath("$[4].item.id").value(lead5.getItem().getId()))
                .andExpect(jsonPath("$[4].item.name").value(lead5.getItem().getName()))
                .andExpect(jsonPath("$[4].item.description").value(lead5.getItem().getDescription()))
                .andExpect(jsonPath("$[4].item.category").value(lead5.getItem().getCategory()))
                .andExpect(jsonPath("$[4].item.brand").value(lead5.getItem().getBrand()))
                .andExpect(jsonPath("$[4].item.model").value(lead5.getItem().getModel()))
                .andExpect(jsonPath("$[4].item.image").value(lead5.getItem().getImage()))
                .andExpect(jsonPath("$[4].item.availability").value(lead5.getItem().isAvailability()));
    }

    @Test
    public void getLeadById_ReturnsLeadById() throws Exception {
        // Arrange
        Lead lead = new Lead(random.nextLong(), randomString(), randomString(), randomEmail(), randomString(), randomString(), null);
        Optional<Lead> leadOptional = Optional.of(lead);
        when(leadService.getLeadById(anyLong())).thenReturn(leadOptional);

        // Act & Assert
        mockMvc.perform(get("/api/leads/{id}", lead.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(lead.getId()))
                .andExpect(jsonPath("$.firstName").value(lead.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(lead.getLastName()))
                .andExpect(jsonPath("$.email").value(lead.getEmail()))
                .andExpect(jsonPath("$.phone").value(lead.getPhone()))
                .andExpect(jsonPath("$.message").value(lead.getMessage()))
                .andExpect(jsonPath("$.item").doesNotExist());
    }

    @Test
    public void saveLead_ReturnsSavedLead() throws Exception {
        // Arrange
        Lead leadToSave = new Lead(null, randomString(), randomString(), randomEmail(), randomString(), randomString(), null);
        Lead savedLead = new Lead(random.nextLong(), leadToSave.getFirstName(), leadToSave.getLastName(), leadToSave.getEmail(), leadToSave.getPhone(), leadToSave.getMessage(), null);
        when(leadService.saveLead(any(Lead.class))).thenReturn(savedLead);

        // Act & Assert
        mockMvc.perform(post("/api/leads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leadToSave)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedLead.getId()))
                .andExpect(jsonPath("$.firstName").value(savedLead.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(savedLead.getLastName()))
                .andExpect(jsonPath("$.email").value(savedLead.getEmail()))
                .andExpect(jsonPath("$.phone").value(savedLead.getPhone()))
                .andExpect(jsonPath("$.message").value(savedLead.getMessage()))
                .andExpect(jsonPath("$.item").doesNotExist());
    }

    @Test
    public void deleteLead_ReturnsSuccess() throws Exception {
        // Arrange
        Long leadId = 1L;
        Optional<Lead> mockLead = Optional.of(new Lead());

        when(leadService.getLeadById(leadId)).thenReturn(mockLead);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/leads/{id}", leadId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(LEAD_CONTROLLER_DELETE_SUCCESS));

        // Verify that the deleteLead method was called with the correct ID
        verify(leadService, times(1)).deleteLead(leadId);
    }

    @Test
    public void deleteLead_ReturnsBadRequestWhenLeadNotFound() throws Exception {
        // Arrange
        Long leadId = 1L;
        Optional<Lead> mockLead = Optional.empty();

        when(leadService.getLeadById(leadId)).thenReturn(mockLead);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/leads/{id}", leadId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(LEAD_CONTROLLER_NO_LEAD_TO_DELETE));

        // Verify that the deleteLead method was not called
        verify(leadService, never()).deleteLead(leadId);
    }

    @Test
    //TODO Fix this
    @Disabled("This test is currently disabled for a specific reason.")
    public void createLead_ReturnsCreatedLead() throws Exception {
        // Arrange
        Map<String, Object> payload = Map.of(
                "firstName", randomString(),
                "lastName", randomString(),
                "email", randomEmail(),
                "phone", randomString(),
                "message", randomString(),
                "item", new Item(1L, "Item1", "Description1", "Category1", "Brand1", "Model1", "Image1.jpg", true));

        // Arrange
        Lead createdLead = new Lead(random.nextLong(), (String) payload.get("firstName"), (String) payload.get("lastName"), (String) payload.get("email"), (String) payload.get("phone"), (String) payload.get("message"), null);
        when(leadService.createLead(anyLong(), anyLong())).thenReturn(String.valueOf(createdLead));

        // Act & Assert
        mockMvc.perform(post("/api/leads/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(createdLead.getId()))
                .andExpect(jsonPath("$.firstName").value(createdLead.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(createdLead.getLastName()))
                .andExpect(jsonPath("$.email").value(createdLead.getEmail()))
                .andExpect(jsonPath("$.phone").value(createdLead.getPhone()))
                .andExpect(jsonPath("$.message").value(createdLead.getMessage()))
                .andExpect(jsonPath("$.item").doesNotExist());
    }
}
