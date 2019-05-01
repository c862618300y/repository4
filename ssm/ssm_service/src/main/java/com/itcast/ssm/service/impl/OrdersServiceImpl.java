package com.itcast.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.ssm.dao.OrdersDao;
import com.itcast.ssm.domain.Orders;
import com.itcast.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单持久层接口实现类
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    /**
     * 查询所有订单信息
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //在执行sql前使用PageHelper来完成分页 page:当前页面  size:每页显示的数据条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    /**
     * 通过订单id查询订单详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Orders findById(String id) throws Exception {
        Orders order=ordersDao.findById(id);
        return order;
    }
}
