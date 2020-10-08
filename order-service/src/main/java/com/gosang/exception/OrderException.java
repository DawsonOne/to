package com.gosang.exception;

import com.gosang.enums.OrderEnum;

public class OrderException extends RuntimeException {

    public OrderException(OrderEnum orderEnum) {
        super(orderEnum.getMsg());
    }
}
