package com.baotao.controller;

import com.baotao.bean.Commodity;
import com.baotao.bean.User;
import com.baotao.service.ICommodityService;
import com.baotao.service.impl.CommodityServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/commodity")
public class CommodityController {

    private ICommodityService commodityService = new CommodityServiceImpl();
    @GetMapping
    public String defaultHandler(Model model) {
        List<Commodity> commodities = commodityService.getCommodity();
        model.addAttribute("list", commodities);
        return "commodity/commodity";
    }
    @GetMapping("/list")
    public String listCommodities(@RequestParam String condition,Model model) {
        List<Commodity> commodity = commodityService.getCommodityCondition(condition);
        System.out.println(commodity);
        model.addAttribute("list", commodity);
        return "commodity/commodity";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("commodity", new Commodity());
        return "commodity/commodityUpdate";
    }

    @PostMapping("/save")
    public String saveCommodity(@ModelAttribute Commodity commodity,Model model) {
        commodityService.addCommodity(commodity);
        // 通过service查询所有的用户信息
        List<Commodity> list = commodityService.getCommodity();
        model.addAttribute("list", list);
        return "/commodity/commodity";
    }

    @GetMapping("/update/{commodityId}")
    public String showUpdateForm(@PathVariable Integer commodityId, Model model) {
        Commodity commodity = commodityService.queryByCommodity_id(commodityId);
        model.addAttribute("commodity", commodity);
        return "commodity/commodityUpdate";
    }

        @PostMapping("/update")
        public String updateCommodity(@ModelAttribute Commodity commodity,Model model) {
            commodityService.updateCommodity(commodity);
            // 通过service查询所有的用户信息
            List<Commodity> list = commodityService.getCommodity();
            model.addAttribute("list", list);
            return "/commodity/commodity";
        }

    @RequestMapping(params = "type=delete")
    public String deleteCommodity(@RequestParam("commodity_id") Integer commodityId) {
        commodityService.deleteByCommodity_id(commodityId);
        return "/commodity/commodity";
    }


    @GetMapping("/commodity")
    public String showCommodity(Model model) {
        // 通过service查询所有的用户信息
        Commodity commodity = new Commodity();
        List<Commodity> list = commodityService.getCommodity();
        model.addAttribute("list", list);
        return "commodity/commodity";
    }
    @GetMapping("/commodityUpdate")
    public String showCommodityUpdate(Model model) {
        return "/commodity/commodityUpdate";
    }
    @GetMapping("/queryById/{commodity_id}")
    public String queryById(@PathVariable Integer commodity_id, Model model) {
        // 通过service查询
        Commodity commodity = commodityService.queryByCommodity_id(commodity_id);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("commodity", commodity);
        // 页面跳转到user.jsp中
        System.out.println(commodity.toString());
        return "commodity/commodityUpdate";
    }
    @RequestMapping(params = "type=buying")
    public String buyingCommodity(
            @RequestParam("commodity_id") Integer commodityId,
            @RequestParam("count") int count,
            @RequestParam("user_id") int userId,
            Model model) {
        Commodity commodity = commodityService.queryByCommodity_id(commodityId);
        Integer flag = commodityService.buyingCommodity(commodity, commodityId, userId, count);

        if (flag > 0) {
            // 购买成功，重新查询所有商品
            List<Commodity> commodities = commodityService.getCommodity();
            model.addAttribute("list", commodities);
            System.out.println("购买成功");
            return "commodity/commodity"; // 返回商品列表页面
        } else {
            // 购买失败，可能需要处理失败的逻辑
            System.out.println("购买失败");
            return "wrongOperate"; // 返回错误页面
        }
    }
}
