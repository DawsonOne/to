package com.gosang.controller;


import com.gosang.enums.OrderEnum;
import com.gosang.exception.OrderException;
import com.gosang.form.OrderForm;
import com.gosang.service.OrderMasterService;
import com.gosang.util.ResultVOUtil;
import com.gosang.vo.OrderVO;
import com.gosang.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author gosang
 * @since 2020-09-26
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderMasterBuyerController {

    @Autowired
    OrderMasterService orderMasterService;

    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("【创建订单】,参数异常,orderForm={}", orderForm);
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        //创建订单
        String orderId = orderMasterService.create(orderForm);
        if (orderId == null) {
            log.info("【创建订单】,创建失败,orderForm={}", orderForm);
            throw new OrderException(OrderEnum.ORDER_CREATE_FAIL);
        }
        Map<String, String> data = new HashMap();
        data.put("orderId", orderId);
        ResultVO<Map<String, String>> resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    @GetMapping("/list/{buyerId}/{page}/{size}")
    public ResultVO list(@PathVariable("buyerId") String buyerId, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return ResultVOUtil.success(orderMasterService.list(buyerId,page,size));
    }

    @GetMapping("/detail/{buyerId}/{orderId}")
    public ResultVO orderDetail(@PathVariable("buyerId") String buyerId, @PathVariable("orderId") String orderId) {
        return ResultVOUtil.success(orderMasterService.orderDetail(buyerId,orderId));
    }

    @PutMapping("/cancel/{buyerId}/{orderId}")
    public ResultVO orderCancel(@PathVariable("buyerId") String buyerId, @PathVariable("orderId") String orderId){
        Boolean aBoolean = orderMasterService.orderCancel(buyerId, orderId);
        if (aBoolean) return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }

    @PutMapping("/finish/{orderId}")
    public ResultVO orderCancel(@PathVariable("orderId") String orderId){
        Boolean aBoolean = orderMasterService.orderFinsh(orderId);
        if (aBoolean) return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }

    @PutMapping("/pay/{buyerId}/{orderId}")
    public ResultVO orderPay(@PathVariable("buyerId") String buyerId, @PathVariable("orderId") String orderId){
        Boolean aBoolean = orderMasterService.orderPay(buyerId, orderId);
        if (aBoolean) return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }
}

