package org.newit.microservice.ebusiness.controller;

import java.util.List;

import org.newit.microservice.ebusiness.model.Item;
import org.newit.microservice.ebusiness.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class ItemController{

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/insert")
    @ResponseBody
    public JSONObject insert(@RequestBody Item item) {
        itemService.insert(item);
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/item/allList")
    @ResponseBody
    public List<Item>  itemAllList(Model model){
        return itemService.getItemAllList();
    }

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public Item itemDetail(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }
}
