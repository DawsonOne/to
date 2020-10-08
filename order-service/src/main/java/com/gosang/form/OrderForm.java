package com.gosang.form;

import com.gosang.entity.OrderDetail;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/9/26 23:38
 */
@Data
public class OrderForm {
    @NotEmpty(message = "买家姓名不能为空")
    private String name;
    @NotEmpty(message = "电话不能为空")
    private String phone;
    @NotEmpty(message = "地址不能为空")
    private String address;
    @NotEmpty(message = "openid不能为空")
    private String id;
    @NotEmpty(message = "购物车不能为空")
    private List<OrderDetail> items;
}
