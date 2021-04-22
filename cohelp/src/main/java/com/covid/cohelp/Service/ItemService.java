package com.covid.cohelp.Service;

import com.covid.cohelp.Entity.Item;
import com.covid.cohelp.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        Optional<Item> itemOptional = itemRepository.findById(item.getItem_id());
        if(itemOptional.isPresent()) {
            Item curItem = itemOptional.get();
            curItem.setItem_name(item.getItem_name());
            return itemRepository.save(curItem);
        }
        else {
            return addItem(item);
        }

    }

    public String removeItem(long id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            itemRepository.delete(item.get());
            return "Success";
        }
        return "Item Didn't exist";
    }
}
