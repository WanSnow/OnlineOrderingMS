package com.wansnow.ordering.controller;

import com.wansnow.ordering.dao.OrderingListDao;
import com.wansnow.ordering.entity.OrderingList;
import com.wansnow.ordering.service.OrderingServiceImpl;
import com.wansnow.ordering.service.ShopServiceImpl;
import com.wansnow.ordering.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller

public class OrderingController {
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderingServiceImpl orderingService;
    @Autowired
    private ShopServiceImpl shopService;
    @Resource(name = "order")
    private SnowFlake orderIdGenerator;

    @RequestMapping(path = "/shopView/{shopId}", method = RequestMethod.GET)
    public String shopView(@PathVariable String shopId){
        session.setAttribute("shopView", shopService.getShop(shopId));
        session.setAttribute("dishView", shopService.getAllDish(shopId));
        return "shopView";
    }

    @RequestMapping(path = "/orderView", method = RequestMethod.GET)
    public String orderView(){
        return "orderView";
    }

    @RequestMapping(path = "/addOrderingList", method = RequestMethod.POST,  produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String addOrderingList(OrderingList orderingList){
        orderingList.setOrderingId(orderIdGenerator.nextId()+"");
        orderingList.setOrdering(false);
        if(orderingService.addOrderingList(orderingList)){
            return "订单已提交，请等待审核！";
        }
        return "订单提交失败！请稍后尝试！";
    }
}
