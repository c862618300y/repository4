package com.itcast.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.ssm.domain.Orders;
import com.itcast.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单界面层
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 查询所有订单--未分页
     * @return
     * @throws Exception
     */
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Orders> list = ordersService.findAll();
        //System.out.println(list.get(0).getOrderNum());
        ModelAndView mav = new ModelAndView();
        mav.addObject("ordersList", list);
        mav.setViewName("orders-list");
        return mav;
    }*/

    /**
     * 查询所有订单--已分页处理
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        List<Orders> list = ordersService.findAll(page,size);
        //创建分页对象
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("orders-page-list");
        return mav;
    }

    /**
     * 通过订单id 查询订单详情
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId) throws Exception {
        ModelAndView mav = new ModelAndView();
        Orders orders=ordersService.findById(ordersId);
        mav.addObject("orders",orders);
        mav.setViewName("orders-show");
        return mav;
    }
}
