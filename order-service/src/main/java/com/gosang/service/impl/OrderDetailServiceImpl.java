package com.gosang.service.impl;

import com.gosang.entity.OrderDetail;
import com.gosang.mapper.OrderDetailMapper;
import com.gosang.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author gosang
 * @since 2020-09-26
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
