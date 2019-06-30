package org.newit.microservice.ebusiness.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.newit.microservice.ebusiness.dao.ItemMapper;
import org.newit.microservice.ebusiness.model.Item;
import org.newit.microservice.ebusiness.model.ItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    @Autowired
    private ItemMapper itemMapper;

    public Item getItemById(long itemId) {
        ItemExample example = new ItemExample();
        example.createCriteria().andIdEqualTo(itemId);
        List<Item> items = itemMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(items)) {
            return items.get(0);
        }
        return null;
    }

    public void insert(Item item) {
        itemMapper.insert(item);
    }

    public List<Item> getItemAllList() {
        ItemExample example = new ItemExample();
        example.createCriteria();
        return itemMapper.selectByExample(example);
    }
}
