package org.newit.microservice.ebusiness.repository;

import java.util.List;

import org.newit.microservice.ebusiness.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@Repository
public class ItemRepository {

    @Autowired
    private RestTemplate restTemplate;

    public Item getItemById(long itemId) {
        Item item = restTemplate.getForObject("http://localhost:6101/item/" + itemId, Item.class);
        return item;
    }

    public void insert(Item item) {
        restTemplate.postForObject("http://localhost:6101/item/insert", item, JSONObject.class);
    }

    public List<Item> getItemAllList() {
        return restTemplate.getForObject("http://localhost:6101/item/allList", List.class);
    }
}
