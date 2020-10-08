package com.gosang.exception;

import com.gosang.enums.ProductEnum;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/9/27 23:54
 */
public class ProductException extends RuntimeException {
    public ProductException(ProductEnum productEnum){
        super(productEnum.getMsg());
    }
}
