package org.newit.microservice.ebusiness.view;

import org.newit.microservice.ebusiness.model.Item;
import org.newit.microservice.ebusiness.model.Order;
import org.newit.microservice.ebusiness.model.User;

import lombok.Data;

@Data
public class OrderView {
    private Order order;
    private User buyer;
    private User seller;
    private Item item;

}
