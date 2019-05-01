package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 旅客持久层接口
 */
public interface TravellerDao {
    /**
     * 通过订单表中的旅客id查询旅客信息
     * @param orderId
     * @return 旅客对象泛型的集合
     */
    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{orderId})")
    public List<Traveller> findByOrderId(String orderId)throws Exception;
}
