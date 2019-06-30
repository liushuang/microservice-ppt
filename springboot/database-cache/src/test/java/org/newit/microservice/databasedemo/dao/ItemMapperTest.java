package org.newit.microservice.databasedemo.dao;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.newit.microservice.databasedemo.model.Item;
import org.newit.microservice.databasedemo.model.ItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void testInsert(){
        Item item = new Item();
        item.setName("test name");
        item.setPrice(42L);
        item.setCreatedTime(Calendar.getInstance().getTime());
        itemMapper.insert(item);
    }

    @Test
    public void testSelectList(){
        ItemExample example = new ItemExample();
        example.createCriteria().andNameLike("%test%").andIdGreaterThanOrEqualTo(2L);
        List<Item> items = itemMapper.selectByExample(example);
        System.out.println(items.size());
    }
}
