package com.baotao.service.impl;

import com.baotao.bean.Orders;
import com.baotao.dao.IOrdersDao;
import com.baotao.dao.impl.OrdersDaoImpl;
import com.baotao.service.IOrdersService;

import java.util.List;

public class OrdersServiceImpl implements IOrdersService {

    private IOrdersDao ordersDao = new OrdersDaoImpl();
    @Override
    public List<Orders> getOrders(Orders orders) {
        return ordersDao.list(orders);
    }

    @Override
    public List<Orders> getOrdersCondition(String str) {
        return ordersDao.listCondition(str);
    }

    @Override
    public Integer addOrders(Orders orders) {
        return ordersDao.save(orders);
    }

    @Override
    public Integer deleteByOrders_id(Integer orders_id) {
        return ordersDao.deleteByOrders_id(orders_id);
    }

    @Override
    public Orders queryByOrders_id(Integer orders_id) {
        return ordersDao.queryByOrders_id(orders_id);
    }

    @Override
    public Integer updateOrders(Orders orders) {
        return ordersDao.updateOrders(orders);
    }
}
