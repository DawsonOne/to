package com.gosang.controller;

import com.gosang.entity.ProductInfo;
import com.gosang.enums.ProductEnum;
import com.gosang.exception.ProductException;
import com.gosang.service.ProductCategoryBuyerService;
import com.gosang.service.ProductInfoService;
import com.gosang.util.ResultVOUtil;
import com.gosang.vo.ProductManageVO;
import com.gosang.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/9/29 21:33
 */
@RestController
@RequestMapping("/seller/product")
@Slf4j
public class ProductCategorySellerController {

    @Autowired
    ProductCategoryBuyerService productCategoryBuyerService;

    @Autowired
    ProductInfoService productInfoService;

    @GetMapping("/findAllProductCategory")
    public ResultVO findAllProductCategory(){
        Map map = new HashMap();
        map.put("content", this.productCategoryBuyerService.findAllProduct());
        return ResultVOUtil.success(map);
    }

    @PostMapping("/add")
    public ResultVO addProduct(@RequestBody ProductInfo productInfo){
        productInfoService.save(productInfo);
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO findAllProduct(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return ResultVOUtil.success(productInfoService.findAllProduct(page,size));
    }

    @GetMapping("findById/{id}")
    public ResultVO findById(@PathVariable("id")Integer id){
        if(id == null){
            log.info("【查询商品】，商品编号为空，id={}", id);
            throw new ProductException(ProductEnum.PRODUCT_ID_NULL);
        }
        return ResultVOUtil.success(productInfoService.findById(id));
    }

    @DeleteMapping("delete/{id}")
    public ResultVO deleteById(@PathVariable("id")Integer id){
        if(productInfoService.deleteById(id)) return ResultVOUtil.success(null);
        else return ResultVOUtil.fail();
    }
    @PutMapping("updateStatus/{id}/{status}")
    public ResultVO updateStatus(@PathVariable("id")Integer id,@PathVariable("status")Boolean status){
        if (productInfoService.updateStatus(id, status)) return ResultVOUtil.success(status);
        return ResultVOUtil.fail();
    }

    @PutMapping("update")
    public ResultVO updateStatus(@RequestBody ProductManageVO productManageVO){
        boolean result = this.productInfoService.updateProductManageVO(productManageVO);
        if(!result) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}
