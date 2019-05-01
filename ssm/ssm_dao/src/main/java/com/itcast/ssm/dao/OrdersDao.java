package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Member;
import com.itcast.ssm.domain.Orders;
import com.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 订单持久层接口
 */
public interface OrdersDao {
    /**
     * 查询所有订单
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results(id="ordersMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itcast.ssm.dao.ProductDao.findById",fetchType = FetchType.EAGER))
    })
    List<Orders> findAll()throws Exception;

    /**
     * 通过订单id查询订单详情
     * @param id
     * @return
     */
    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itcast.ssm.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one=@One(select="com.itcast.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class ,many = @Many(select = "com.itcast.ssm.dao.TravellerDao.findByOrderId"))
    })
    Orders findById(String id)throws Exception;
}
