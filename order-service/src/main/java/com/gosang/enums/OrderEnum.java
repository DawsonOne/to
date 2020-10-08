package com.gosang.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/9/28 0:38
 */
@Getter
public enum OrderEnum {

    ORDER_CREATE_FAIL(0,"订单创建失败"),
    ORDER_PARAM_ERROR(1,"订单参数异常"),
    PRODUCT_NOT_EXIST(2,"商品不存在"),
    ORDER_NOT_EXIST(3,"订单不存在"),
    ORDER_STATUS_ERROR(4,"订单状态异常"),
    PAY_STATUS_kERROR(5,"支付状态异常");

    OrderEnum(Integer id,String msg){
        this.id = id;
        this.msg = msg;
    }

    private Integer id;
    private String msg;
}
