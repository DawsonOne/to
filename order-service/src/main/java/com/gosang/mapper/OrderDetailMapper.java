package com.gosang.mapper;

import com.gosang.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author gosang
 * @since 2020-09-26
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    public BigDecimal getPriceById(Integer id);
}
