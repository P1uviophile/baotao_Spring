package com.baotao.controller;

import com.baotao.bean.Orders;
import com.baotao.service.IOrdersService;
import com.baotao.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private IOrdersService ordersService = new OrdersServiceImpl();

    @GetMapping
    public String defaultHandler(Model model) {
        // 默认查询所有订单信息
        queryOrdersAll(model);
        return "orders/orders";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateOrders(@ModelAttribute Orders orders, Model model) {
        try {
            Integer count = -1;
            if (orders.getOrder_id() != null && orders.getOrder_id() > 0) {
                // 更新订单
                count = ordersService.updateOrders(orders);
            } else {
                // 添加订单
                count = ordersService.addOrders(orders);
            }

            if (count > 0) {
                // 查询成功，再做一次查询操作
                queryOrdersAll(model);
            } else {
                // 插入失败，跳转到失败页面
                return "wrongOperate";
            }

        } catch (Exception e) {
            // 插入失败，跳转到失败页面
            return "wrongOperate";
        }

        return "orders/orders";
    }

    @GetMapping("/delete/{order_id}")
    public String deleteOrders(@PathVariable Integer order_id) {
        // 通过Service处理删除操作
        Integer count = ordersService.deleteByOrders_id(order_id);
        // 重定向查询所有订单
        return "redirect:/orders";
    }

    @GetMapping("/query")
    public String queryOrdersCondition(@RequestParam String condition, Model model) {
        // 通过service查询
        List<Orders> list = ordersService.getOrdersCondition(condition);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("list", list);
        return "orders/orders";
    }

    @GetMapping("/queryById/{order_id}")
    public String queryById(@PathVariable Integer order_id, Model model) {
        // 通过service查询
        Orders orders = ordersService.queryByOrders_id(order_id);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("orders", orders);
        return "orders/ordersUpdate";
    }

    private void queryOrdersAll(Model model) {
        // 通过service查询所有的订单信息
        List<Orders> list = ordersService.getOrders(null);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("list", list);
    }

    @GetMapping("/ordersUpdate")
    public String showUserUpdatePage() {
        return "orders/ordersUpdate";
    }
}
