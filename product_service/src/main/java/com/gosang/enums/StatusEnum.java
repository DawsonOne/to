package com.gosang.enums;

import com.sun.javaws.jnl.IconDesc;
import lombok.Getter;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/10/7 10:43
 */
@Getter
public enum StatusEnum {

    STATUS_TRUE(1,true),
    STATUS_FALSE(0,false);


    StatusEnum(Integer code, Boolean status){
        this.code = code;
        this.status = status;
    }

    private Integer code;
    private Boolean status;
}
