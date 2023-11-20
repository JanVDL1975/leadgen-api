package com.leadgen.api.service;

import com.leadgen.api.entity.Item;
import com.leadgen.api.repository.ItemRepository;
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
public class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    public void getAllItems_ShouldReturnAllItems() {
        // Arrange
        Item item1 = new Item(1L, "Item 1", "Description 1", "Category 1", "Brand 1", "Model 1", "Image 1.jpg", true);
        Item item2 = new Item(2L, "Item 2", "Description 2", "Category 2", "Brand 2", "Model 2", "Image 2.jpg", false);
        Mockito.when(itemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        // Act
        List<Item> result = itemService.getAllItems();

        // Assert
        assertEquals(2, result.size());
        assertEquals(item1, result.get(0));
        assertEquals(item2, result.get(1));
    }

    @Test
    public void getAvailableItems_ShouldReturnAvailableItems() {
        // Arrange
        Item item1 = new Item(1L, "Item 1", "Description 1", "Category 1", "Brand 1", "Model 1", "Image 1.jpg", true);
        Item item2 = new Item(2L, "Item 2", "Description 2", "Category 2", "Brand 2", "Model 2", "Image 2.jpg", false);
        Mockito.when(itemRepository.findByAvailability(true)).thenReturn(Arrays.asList(item1));

        // Act
        List<Item> result = itemService.getAvailableItems();

        // Assert
        assertEquals(1, result.size());
        assertEquals(item1, result.get(0));
    }

    @Test
    public void getItemById_ShouldReturnItemById() {
        // Arrange
        Long itemId = 1L;
        Item item = new Item(itemId, "Item 1", "Description 1", "Category 1", "Brand 1", "Model 1", "Image 1.jpg", true);
        Mockito.when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // Act
        Optional<Item> result = itemService.getItemById(itemId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(item, result.get());
    }

    @Test
    public void saveItem_ShouldSaveItem() {
        // Arrange
        Item item = new Item(1L, "Item 1", "Description 1", "Category 1", "Brand 1", "Model 1", "Image 1.jpg", true);
        Mockito.when(itemRepository.save(item)).thenReturn(item);

        // Act
        Item result = itemService.saveItem(item);

        // Assert
        assertEquals(item, result);
    }

    @Test
    public void deleteItem_ShouldDeleteItem() {
        // Arrange
        Long itemId = 1L;

        // Act
        itemService.deleteItem(itemId);

        // Verify that the deleteById method was called with the correct ID
        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(itemId);
    }
}

