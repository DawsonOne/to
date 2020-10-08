package com.gosang.controller;


import com.gosang.service.ProductCategoryBuyerService;
import com.gosang.vo.ProductCategoryVO;
import com.gosang.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author gosang
 * @since 2020-09-24
 */
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class ProductCategoryBuyerController {

    @Value("${server.port}")
    private String port;

    @Autowired
    ProductCategoryBuyerService productCategoryBuyerService;

    @GetMapping("/list")
    public ResultVO findAllProduct(){
        log.info("调用了{}端口的服务",this.port);
        ResultVO<List<ProductCategoryVO>> resultVO = productCategoryBuyerService.ProductCategoryList();
        return resultVO;
    }

    @GetMapping("/port")
    public String  getPort(){
        return port;
    }
}

