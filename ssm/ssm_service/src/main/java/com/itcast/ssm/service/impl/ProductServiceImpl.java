package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.ProductDao;
import com.itcast.ssm.domain.Product;
import com.itcast.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品持久层接口实现类
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    /**
     * 查询全部产品信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    /**
     * 添加产品
     * @param product
     * @throws Exception
     */
    @Override
    public void save(Product product) throws Exception {
        System.out.println("添加执行了。。。");
        productDao.save(product);
    }
}
