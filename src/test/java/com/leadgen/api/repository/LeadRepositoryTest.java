package com.leadgen.api.repository;

import com.leadgen.api.entity.Lead;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LeadRepositoryTest {

    @Mock
    private LeadRepository leadRepository;

    @Test
    public void findLeadById_ShouldReturnLeadById() {
        // Arrange
        Long leadId = 1L;
        Lead lead = new Lead(leadId, "John", "Doe", "john@example.com", "+123456789", "Hello", null);
        Mockito.when(leadRepository.findLeadById(leadId)).thenReturn(Optional.of(lead));

        // Act
        Optional<Lead> result = leadRepository.findLeadById(leadId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(lead, result.get());
    }

    @Test
    public void findLeadByEmail_ShouldReturnLeadByEmail() {
        // Arrange
        String email = "john@example.com";
        Lead lead = new Lead(1L, "John", "Doe", email, "+123456789", "Hello", null);
        Mockito.when(leadRepository.findLeadByEmail(email)).thenReturn(lead);

        // Act
        Lead result = leadRepository.findLeadByEmail(email);

        // Assert
        assertEquals(lead, result);
    }

    @Test
    public void findAll_ShouldReturnAllLeads() {
        // Arrange
        Lead lead1 = new Lead(1L, "John", "Doe", "john@example.com", "+123456789", "Hello", null);
        Lead lead2 = new Lead(2L, "Jane", "Doe", "jane@example.com", "+987654321", "Hi", null);
        Mockito.when(leadRepository.findAll()).thenReturn(Arrays.asList(lead1, lead2));

        // Act
        List<Lead> result = leadRepository.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(lead1, result.get(0));
        assertEquals(lead2, result.get(1));
    }
}

