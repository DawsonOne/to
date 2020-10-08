package com.gosang.controller;


import com.gosang.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author gosang
 * @since 2020-09-24
 */
@RestController
@RequestMapping("/productInfo")
@Slf4j
public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    @PutMapping("/subStock/{id}/{quantity}")
    public boolean subStock(@PathVariable("id") Integer id,@PathVariable("quantity") Integer quantity){
        return productInfoService.subStock(id, quantity);
    }
}

