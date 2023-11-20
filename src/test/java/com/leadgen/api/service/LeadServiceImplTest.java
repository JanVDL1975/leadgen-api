package com.leadgen.api.service;

import com.leadgen.api.constants.ApplicationConstants;
import com.leadgen.api.entity.Item;
import com.leadgen.api.entity.Lead;
import com.leadgen.api.repository.ItemRepository;
import com.leadgen.api.repository.LeadRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LeadServiceImplTest {

    @Mock
    private LeadRepository leadRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private LeadServiceImpl leadService;

    @Test
    public void getAllLeads_ShouldReturnAllLeads() {
        // Arrange
        Lead lead1 = new Lead(1L, "John", "Doe", "john@example.com", "+123456789", "Hello", null);
        Lead lead2 = new Lead(2L, "Jane", "Doe", "jane@example.com", "+987654321", "Hi", null);
        Mockito.when(leadRepository.findAll()).thenReturn(Arrays.asList(lead1, lead2));

        // Act
        List<Lead> result = leadService.getAllLeads();

        // Assert
        assertEquals(2, result.size());
        assertEquals(lead1, result.get(0));
        assertEquals(lead2, result.get(1));
    }

    @Test
    public void getLeadById_ShouldReturnLeadById() {
        // Arrange
        Long leadId = 1L;
        Lead lead = new Lead(leadId, "John", "Doe", "john@example.com", "+123456789", "Hello", null);
        Mockito.when(leadRepository.findById(leadId)).thenReturn(Optional.of(lead));

        // Act
        Optional<Lead> result = leadService.getLeadById(leadId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(lead, result.get());
    }

    @Test
    public void saveLead_ShouldSaveLead() {
        // Arrange
        Lead lead = new Lead(1L, "John", "Doe", "john@example.com", "+123456789", "Hello", null);
        Mockito.when(leadRepository.save(lead)).thenReturn(lead);

        // Act
        Lead result = leadService.saveLead(lead);

        // Assert
        assertEquals(lead, result);
    }

    @Test
    public void deleteLead_ShouldDeleteLead() {
        // Arrange
        Long leadId = 1L;

        // Act
        leadService.deleteLead(leadId);

        // Verify that the deleteById method was called with the correct ID
        Mockito.verify(leadRepository, Mockito.times(1)).deleteById(leadId);
    }

    @Test
    public void createLead_ShouldCreateLead() {
        // Arrange
        Long itemId = 1L;
        Long leadId = 2L;
        Item item = new Item(itemId, "Item 1", "Description", "Category", "Brand", "Model", "Image.jpg", true);
        Lead lead = new Lead(leadId, "John", "Doe", "john@example.com", "+123456789", "Hello", null);

        Mockito.when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        Mockito.when(leadRepository.findLeadById(leadId)).thenReturn(Optional.of(lead));

        // Act
        String result = leadService.createLead(itemId, leadId);

        // Assert
        assertEquals(ApplicationConstants.LEAD_SERVICE_CREATE_SUCCESS, result);
        assertEquals(item, lead.getItem());
        Mockito.verify(leadRepository, Mockito.times(1)).save(lead);
    }
}

