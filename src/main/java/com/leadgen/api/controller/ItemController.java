package com.leadgen.api.controller;

import com.leadgen.api.entity.Item;
import com.leadgen.api.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping(value = "/api/items")
public class ItemController {
    @Autowired
    private ItemServiceImpl itemServiceImpl;

    @GetMapping
    public List<Item> getAllItems() {
        return itemServiceImpl.getAllItems();
    }

    @GetMapping("/available")
    public List<Item>
    getAvailableItems() {
        return itemServiceImpl.getAvailableItems();
    }

    @GetMapping("/{id}")
    public Optional<Item> getItemById(@PathVariable("id") Long id) {
        return itemServiceImpl.getItemById(id);
    }

    @PostMapping
    public Item saveItem(@RequestBody Item item) {
        return itemServiceImpl.saveItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemServiceImpl.deleteItem(id);
    }
}