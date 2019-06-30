package org.newit.microservice.ebusiness.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.newit.microservice.ebusiness.model.Item;
import org.newit.microservice.ebusiness.model.Order;
import org.newit.microservice.ebusiness.model.User;
import org.newit.microservice.ebusiness.repository.OrderRepository;
import org.newit.microservice.ebusiness.view.OrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public OrderView getOrderView(Order order){
        OrderView orderView = new OrderView();
        orderView.setOrder(order);
        orderView.setBuyer(userService.getUserById(order.getBuyerId()));
        orderView.setSeller(userService.getUserById(order.getSellerId()));
        orderView.setItem(itemService.getItemById(order.getItemId()));
        return orderView;
    }
    public void createOrder(Long itemId, String username) {
        Item item = itemService.getItemById(itemId);
        User currentUser = userService.getUserByName(username);
        Order order = new Order();
        order.setBuyerId(currentUser.getId());
        order.setSellerId(item.getSellerId());
        order.setItemId(item.getId());
        order.setPrice(item.getPrice());
        order.setCreatedTime(Calendar.getInstance().getTime());
        orderRepository.insert(order);
    }

    public List<Order> getOrderListByBuyerId(long buyerId) {
        return orderRepository.getOrderListByBuyerId(buyerId);
    }

    public List<Order> getOrderListBySellerId(long sellerId) {
        return orderRepository.getOrderListBySellerId(sellerId);
    }

}
