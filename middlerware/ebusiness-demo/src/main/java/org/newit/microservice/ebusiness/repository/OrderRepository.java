package org.newit.microservice.ebusiness.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.newit.microservice.ebusiness.dao.OrderMapper;
import org.newit.microservice.ebusiness.model.Order;
import org.newit.microservice.ebusiness.model.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    public Order getOrderById(Long orderId) {
        OrderExample example = new OrderExample();
        example.createCriteria().andIdEqualTo(orderId);
        List<Order> orders = orderMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(orders)) {
            return orders.get(0);
        }
        return null;
    }

    public void insert(Order order) {
        orderMapper.insertSelective(order);
    }

    public List<Order> getOrderListByBuyerId(long buyerId) {
        OrderExample query = new OrderExample();
        query.createCriteria().andBuyerIdEqualTo(buyerId);
        return orderMapper.selectByExample(query);
    }

    public List<Order> getOrderListBySellerId(long sellerId) {
        OrderExample query = new OrderExample();
        query.createCriteria().andSellerIdEqualTo(sellerId);
        return orderMapper.selectByExample(query);
    }
}
