package com.leadgen.api.service;

import com.leadgen.api.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> getAllItems();

    List<Item> getAvailableItems();

    Optional<Item> getItemById(Long id);

    Item saveItem(Item item);

    void deleteItem(Long id);
}
