package com.gosang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gosang.entity.OrderMaster;
import com.gosang.form.OrderForm;
import com.gosang.vo.OrderVO;
import com.gosang.vo.PageVO;
import com.gosang.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author gosang
 * @since 2020-09-26
 */
public interface OrderMasterService extends IService<OrderMaster> {
    String create(OrderForm orderForm);
    List<OrderVO> list(String buyerId, Integer page, Integer size);
    OrderVO orderDetail(String buyerId, String orderId);
    Boolean orderCancel(String buyerId, String orderId);
    Boolean orderFinsh(String orderId);
    Boolean orderPay(String buyerId, String orderId);
    PageVO<List<OrderMaster>> getOrderByPage(Integer page, Integer size);
    boolean handler(String type,String orderId);
}
