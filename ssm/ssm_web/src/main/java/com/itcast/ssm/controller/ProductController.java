package com.itcast.ssm.controller;

import com.itcast.ssm.domain.Product;
import com.itcast.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 产品界面层
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService iProductService;

    /**
     * 查詢全部产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Product> list = iProductService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("productList",list);
        mav.setViewName("product-list");
        return mav;
    }

    /**
     * 产品添加方法
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
      iProductService.save(product);
      return "redirect:findAll.do";
    }
}
