package org.newit.microservice.ebusiness.service;

import java.util.List;

import org.newit.microservice.ebusiness.model.Item;
import org.newit.microservice.ebusiness.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getItemById(long itemId) {
        return itemRepository.getItemById(itemId);
    }

    public void insert(Item item) {
        itemRepository.insert(item);
    }

    public List<Item> getItemAllList() {
        return itemRepository.getItemAllList();
    }
}
