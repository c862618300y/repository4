package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 产品持久层接口
 */
public interface ProductDao {
    /**
     * 查询所有产品
     *
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    /**
     * 根据产品id查询查询产品详细信息
     *
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    public Product findById(String id)throws Exception;

    /**
     * 产品添加
     *
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product)throws Exception;
}
