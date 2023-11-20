package com.leadgen.api.repository;

import com.leadgen.api.entity.Item;
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
public class ItemRepositoryTest {

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void findItemById_ShouldReturnItemById() {
        // Arrange
        Long itemId = 1L;
        Item item = new Item(itemId, "Item 1", "Description", "Category", "Brand", "Model", "Image.jpg", true);
        Mockito.when(itemRepository.findItemById(itemId)).thenReturn(item);

        // Act
        Item result = itemRepository.findItemById(itemId);

        // Assert
        assertEquals(item, result);
    }

    @Test
    public void findByAvailability_ShouldReturnItemsByAvailability() {
        // Arrange
        boolean availability = true;
        Item item1 = new Item(1L, "Item 1", "Description", "Category", "Brand", "Model", "Image.jpg", availability);
        Item item2 = new Item(2L, "Item 2", "Description", "Category", "Brand", "Model", "Image.jpg", availability);
        Mockito.when(itemRepository.findByAvailability(availability)).thenReturn(Arrays.asList(item1, item2));

        // Act
        List<Item> result = itemRepository.findByAvailability(availability);

        // Assert
        assertEquals(2, result.size());
        assertEquals(item1, result.get(0));
        assertEquals(item2, result.get(1));
    }

    @Test
    public void findAll_ShouldReturnAllItems() {
        // Arrange
        Item item1 = new Item(1L, "Item 1", "Description", "Category", "Brand", "Model", "Image.jpg", true);
        Item item2 = new Item(2L, "Item 2", "Description", "Category", "Brand", "Model", "Image.jpg", true);
        Mockito.when(itemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        // Act
        List<Item> result = itemRepository.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(item1, result.get(0));
        assertEquals(item2, result.get(1));
    }
}

