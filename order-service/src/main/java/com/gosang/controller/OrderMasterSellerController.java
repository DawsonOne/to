package com.gosang.controller;

import com.gosang.service.OrderMasterService;
import com.gosang.util.ResultVOUtil;
import com.gosang.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/10/7 16:46
 */
@RestController
@RequestMapping("/seller/order")
@Slf4j
public class OrderMasterSellerController {

    @Autowired
    private OrderMasterService orderMasterService;

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return ResultVOUtil.success(this.orderMasterService.getOrderByPage(page, size));
    }

    @PutMapping("/cancel/{orderId}")
    public ResultVO cancel(@PathVariable("orderId") String orderId){
        boolean result = this.orderMasterService.handler("cancel", orderId);
        if(!result) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @PutMapping("/finish/{orderId}")
    public ResultVO finish(@PathVariable("orderId") String orderId){
        boolean result = this.orderMasterService.handler("finish", orderId);
        if(!result) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}
