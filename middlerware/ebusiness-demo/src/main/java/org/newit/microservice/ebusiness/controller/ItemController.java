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
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/create")
    public String itemCreate(){
        return "item/itemCreate";
    }

    @RequestMapping("/item/publish")
    @ResponseBody
    public JSONObject itemPublish(@RequestBody Item item) {
        item.setSellerId(getLoginUser().getId());
        itemService.insert(item);
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/item/allList")
    public String itemAllList(Model model){
        List<Item> itemList = itemService.getItemAllList();
        model.addAttribute("itemList", itemList);
        return "/item/itemList";
    }

    @RequestMapping("/item/detail/{itemId}")
    public String itemDetail(@PathVariable long itemId, Model model) {
        Item item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        return "item/itemDetail";
    }
}
