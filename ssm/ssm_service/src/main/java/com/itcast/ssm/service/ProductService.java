package com.itcast.ssm.service;

import com.itcast.ssm.domain.Product;

import java.util.List;

/**
 * 产品业务层接口
 */
public interface ProductService {
    /**
     * 查询所有产品
     *
     * @return
     * @throws Exception
     */
    public List<Product> findAll() throws Exception;

    /**
     * 产品添加
     *
     * @param product
     */
    public void save(Product product) throws Exception;
}
