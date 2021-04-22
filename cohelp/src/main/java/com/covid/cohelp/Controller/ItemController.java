package com.covid.cohelp.Controller;

import com.covid.cohelp.Entity.Item;
import com.covid.cohelp.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
    @PostMapping("/item")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @PutMapping("/item/")
    public Item updateItem(@RequestBody Item item) {
        return itemService.updateItem(item);
    }



}
