package com.leadgen.api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void defaultConstructor_ShouldCreateObject() {
        Item item = new Item();
        assertNotNull(item);
    }

    @Test
    public void parameterizedConstructor_ShouldSetFields() {
        Item item = new Item(1L, "TestItem", "Description", "Category", "Brand", "Model", "Image.jpg", true);

        assertEquals(1L, item.getId());
        assertEquals("TestItem", item.getName());
        assertEquals("Description", item.getDescription());
        assertEquals("Category", item.getCategory());
        assertEquals("Brand", item.getBrand());
        assertEquals("Model", item.getModel());
        assertEquals("Image.jpg", item.getImage());
        assertTrue(item.isAvailability());
    }

    @Test
    public void gettersAndSetters_ShouldWorkCorrectly() {
        Item item = new Item();

        item.setId(2L);
        item.setName("NewName");
        item.setDescription("NewDescription");
        item.setCategory("NewCategory");
        item.setBrand("NewBrand");
        item.setModel("NewModel");
        item.setImage("NewImage.jpg");
        item.setAvailability(false);

        assertEquals(2L, item.getId());
        assertEquals("NewName", item.getName());
        assertEquals("NewDescription", item.getDescription());
        assertEquals("NewCategory", item.getCategory());
        assertEquals("NewBrand", item.getBrand());
        assertEquals("NewModel", item.getModel());
        assertEquals("NewImage.jpg", item.getImage());
        assertFalse(item.isAvailability());
    }

    @Test
    public void equalsAndHashCode_ShouldWorkCorrectly() {
        Item item1 = new Item(1L, "Item1", "Description1", "Category1", "Brand1", "Model1", "Image1.jpg", true);
        Item item2 = new Item(1L, "Item1", "Description1", "Category1", "Brand1", "Model1", "Image1.jpg", true);
        Item item3 = new Item(2L, "Item2", "Description2", "Category2", "Brand2", "Model2", "Image2.jpg", false);

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);

        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }
}

