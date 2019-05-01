package com.itcast.ssm.service;

import com.itcast.ssm.domain.Orders;

import java.util.List;

/**
 * 订单业务层接口
 */
public interface OrdersService {
    /**
     * 查询所有订单
     * @return
     * @throws Exception
     * @param page
     * @param size
     */
    List<Orders> findAll(int page, int size)throws Exception;

    /**
     * 通过订单id查询订单详情
     * @param id
     * @return
     */
    Orders findById(String id) throws Exception;
}
