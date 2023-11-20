package com.leadgen.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadgen.api.entity.Item;
import com.leadgen.api.controller.ItemController;
import com.leadgen.api.service.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemServiceImpl itemService;

    @Test
    public void getAllItems_ReturnsListOfItems() throws Exception {
        // Arrange
        Item item1 = new Item(1L, "Item 1", "Description 1", "Category 1", "Brand 1", "Model 1", "Image 1", true);
        Item item2 = new Item(2L, "Item 2", "Description 2", "Category 2", "Brand 2", "Model 2", "Image 2", false);
        Mockito.when(itemService.getAllItems()).thenReturn(Arrays.asList(item1, item2));

        // Act & Assert
        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Item 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].category").value("Category 1"))
                .andExpect(jsonPath("$[0].brand").value("Brand 1"))
                .andExpect(jsonPath("$[0].model").value("Model 1"))
                .andExpect(jsonPath("$[0].image").value("Image 1"))
                .andExpect(jsonPath("$[0].availability").value(true))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Item 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].category").value("Category 2"))
                .andExpect(jsonPath("$[1].brand").value("Brand 2"))
                .andExpect(jsonPath("$[1].model").value("Model 2"))
                .andExpect(jsonPath("$[1].image").value("Image 2"))
                .andExpect(jsonPath("$[1].availability").value(false));
    }

    @Test
    public void getAvailableItems_ReturnsListOfAvailableItems() throws Exception {
        // Arrange
        Item item1 = new Item(1L, "Item 1", "Description 1", "Category 1", "Brand 1", "Model 1", "Image 1", true);
        Item item2 = new Item(2L, "Item 2", "Description 2", "Category 2", "Brand 2", "Model 2", "Image 2", false);
        Mockito.when(itemService.getAvailableItems()).thenReturn(Arrays.asList(item1, item2));

        // Act & Assert
        mockMvc.perform(get("/api/items/available"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Item 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].category").value("Category 1"))
                .andExpect(jsonPath("$[0].brand").value("Brand 1"))
                .andExpect(jsonPath("$[0].model").value("Model 1"))
                .andExpect(jsonPath("$[0].image").value("Image 1"))
                .andExpect(jsonPath("$[0].availability").value(true))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Item 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].category").value("Category 2"))
                .andExpect(jsonPath("$[1].brand").value("Brand 2"))
                .andExpect(jsonPath("$[1].model").value("Model 2"))
                .andExpect(jsonPath("$[1].image").value("Image 2"))
                .andExpect(jsonPath("$[1].availability").value(false));
    }

    @Test
    public void getItemById_ReturnsItemById() throws Exception {
        // Arrange
        Long itemId = 1L;
        Item item = new Item(itemId, "Item 1", "Description 1", "Category 1", "Brand 1", "Model 1", "Image 1", true);
        Mockito.when(itemService.getItemById(itemId)).thenReturn(Optional.of(item));

        // Act & Assert
        mockMvc.perform(get("/api/items/{id}", itemId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(itemId))
                .andExpect(jsonPath("$.name").value("Item 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.category").value("Category 1"))
                .andExpect(jsonPath("$.brand").value("Brand 1"))
                .andExpect(jsonPath("$.model").value("Model 1"))
                .andExpect(jsonPath("$.image").value("Image 1"))
                .andExpect(jsonPath("$.availability").value(true));
    }

    @Test
    public void saveItem_SavesNewItem() throws Exception {
        // Arrange
        Item item = new Item(1L, "New Item", "New Description", "New Category", "New Brand", "New Model", "New Image", true);
        Item savedItem = new Item(1L, "New Item", "New Description", "New Category", "New Brand", "New Model", "New Image", true);
        Mockito.when(itemService.saveItem(any(Item.class))).thenReturn(savedItem);

        // Act & Assert
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Item"))
                .andExpect(jsonPath("$.description").value("New Description"))
                .andExpect(jsonPath("$.category").value("New Category"))
                .andExpect(jsonPath("$.brand").value("New Brand"))
                .andExpect(jsonPath("$.model").value("New Model"))
                .andExpect(jsonPath("$.image").value("New Image"))
                .andExpect(jsonPath("$.availability").value(true));
    }

    @Test
    public void deleteItem_DeletesItemById() throws Exception {
        // Arrange
        Long itemId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/api/items/{id}", itemId))
                .andExpect(status().isOk());

        // Verify that the deleteItem method was called with the correct ID
        Mockito.verify(itemService, Mockito.times(1)).deleteItem(eq(itemId));
    }
}
