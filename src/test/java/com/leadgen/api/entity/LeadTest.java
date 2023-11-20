package com.leadgen.api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeadTest {

    @Test
    public void defaultConstructor_ShouldCreateObject() {
        Lead lead = new Lead();
        assertNotNull(lead);
    }

    @Test
    public void parameterizedConstructor_ShouldSetFields() {
        Item item = new Item(1L, "TestItem", "Description", "Category", "Brand", "Model", "Image.jpg", true);
        Lead lead = new Lead(1L, "John", "Doe", "john@example.com", "+123456789", "Hello", item);

        assertEquals(1L, lead.getId());
        assertEquals("John", lead.getFirstName());
        assertEquals("Doe", lead.getLastName());
        assertEquals("john@example.com", lead.getEmail());
        assertEquals("+123456789", lead.getPhone());
        assertEquals("Hello", lead.getMessage());
        assertEquals(item, lead.getItem());
    }

    @Test
    public void gettersAndSetters_ShouldWorkCorrectly() {
        Lead lead = new Lead();

        lead.setId(2L);
        lead.setFirstName("Jane");
        lead.setLastName("Doe");
        lead.setEmail("jane@example.com");
        lead.setPhone("+987654321");
        lead.setMessage("Hi");
        Item item = new Item(1L, "NewItem", "NewDescription", "NewCategory", "NewBrand", "NewModel", "NewImage.jpg", false);
        lead.setItem(item);

        assertEquals(2L, lead.getId());
        assertEquals("Jane", lead.getFirstName());
        assertEquals("Doe", lead.getLastName());
        assertEquals("jane@example.com", lead.getEmail());
        assertEquals("+987654321", lead.getPhone());
        assertEquals("Hi", lead.getMessage());
        assertEquals(item, lead.getItem());
    }

    @Test
    public void equalsAndHashCode_ShouldWorkCorrectly() {
        Item item1 = new Item(1L, "Item1", "Description1", "Category1", "Brand1", "Model1", "Image1.jpg", true);
        Item item2 = new Item(1L, "Item1", "Description1", "Category1", "Brand1", "Model1", "Image1.jpg", true);
        Item item3 = new Item(2L, "Item2", "Description2", "Category2", "Brand2", "Model2", "Image2.jpg", false);

        Lead lead1 = new Lead(1L, "John", "Doe", "john@example.com", "+123456789", "Hello", item1);
        Lead lead2 = new Lead(1L, "John", "Doe", "john@example.com", "+123456789", "Hello", item2);
        Lead lead3 = new Lead(2L, "Jane", "Doe", "jane@example.com", "+987654321", "Hi", item3);

        assertEquals(lead1, lead2);
        assertNotEquals(lead1, lead3);

        assertEquals(lead1.hashCode(), lead2.hashCode());
        assertNotEquals(lead1.hashCode(), lead3.hashCode());
    }
}

