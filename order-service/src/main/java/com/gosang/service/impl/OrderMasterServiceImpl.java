package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gosang.entity.OrderDetail;
import com.gosang.entity.OrderMaster;
import com.gosang.entity.ProductInfo;
import com.gosang.enums.OrderEnum;
import com.gosang.exception.OrderException;
import com.gosang.feign.ProductFeign;
import com.gosang.form.OrderForm;
import com.gosang.mapper.OrderDetailMapper;
import com.gosang.mapper.OrderMasterMapper;
import com.gosang.mapper.ProductInfoMapper;
import com.gosang.service.OrderMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gosang.util.OrderFormToOrderMaster;
import com.gosang.vo.OrderVO;
import com.gosang.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author gosang
 * @since 2020-09-26
 */
@Service
@Slf4j
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService {

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    ProductFeign productFeign;

    /*@Autowired
    private RocketMQTemplate rocketMQTemplate;*/

    @Override
    public String create(OrderForm orderForm) {
        //保存OrderMasetr
        OrderMaster transforOrder = OrderFormToOrderMaster.transfor(orderForm);
        List<OrderDetail> items = orderForm.getItems();
        BigDecimal amout = new BigDecimal(0);
        for (OrderDetail item : items) {
            amout = amout.add(productInfoMapper.getPriceById(item.getProductId()).multiply(new BigDecimal(item.getProductQuantity())));
            //减库存
            this.productFeign.subStock(item.getProductId(),item.getProductQuantity());
        }
        transforOrder.setOrderAmount(amout);
        int orderMasterRow = orderMasterMapper.insert(transforOrder);
        //保存订单详情
        for (OrderDetail item : items) {
            //保存订单详情
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(transforOrder.getOrderId());
            ProductInfo productInfo = this.productInfoMapper.selectById(item.getProductId());
            orderDetail.setProductQuantity(item.getProductQuantity());
            orderDetail.setProductId(item.getProductId());
            orderDetail.setProductIcon(productInfo.getProductIcon());
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            this.orderDetailMapper.insert(orderDetail);
        }
        //this.rocketMQTemplate.convertAndSend("myTopic","有新的订单");
        if (orderMasterRow == 1) return transforOrder.getOrderId();
        return null;
    }

    @Override
    public List<OrderVO> list(String buyerId, Integer page, Integer size){
        if (buyerId == null){
            log.info("【订单详情】，参数异常，buyerId={}", buyerId);
            throw new OrderException(OrderEnum.ORDER_PARAM_ERROR);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("buyer_openid", buyerId);
        Page<OrderMaster> orderMasterPage = new Page<>(page,size);
        Page<OrderMaster> result = this.orderMasterMapper.selectPage(orderMasterPage, wrapper);
        List<OrderMaster> OrderMasterList = result.getRecords();
        List<OrderVO> orderVOList = new ArrayList<>();
        OrderVO orderVO;
        for (OrderMaster orderMaster : OrderMasterList) {
            orderVO = new OrderVO();
            BeanUtils.copyProperties(orderMaster,orderVO);
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }

    @Override
    public OrderVO orderDetail(String buyerId, String orderId) {
        if (buyerId == null){
            log.info("【订单详情】，参数异常，buyerId={}", buyerId);
            throw new OrderException(OrderEnum.ORDER_PARAM_ERROR);
        }
        if (orderId == null){
            log.info("【订单详情】，参数异常，buyerId={}", buyerId);
            throw new OrderException(OrderEnum.ORDER_PARAM_ERROR);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_id", orderId);
        wrapper.eq("buyer_openid", buyerId);
        OrderMaster orderMaster = this.orderMasterMapper.selectOne(wrapper);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderMaster,orderVO);
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("order_id", orderId);
        List<OrderDetail> orderDetailList = this.orderDetailMapper.selectList(wrapper1);
        orderVO.setOrderDetailList(orderDetailList);
        return orderVO;
    }

    @Override
    public Boolean orderCancel(String buyerId, String orderId) {
        if (buyerId == null){
            log.info("【订单详情】，参数异常，buyerId={}", buyerId);
            throw new OrderException(OrderEnum.ORDER_PARAM_ERROR);
        }
        if (orderId == null){
            log.info("【订单详情】，参数异常，buyerId={}", orderId);
            throw new OrderException(OrderEnum.ORDER_PARAM_ERROR);
        }
        int row = this.orderMasterMapper.cancel(buyerId, orderId);
        if(row == 1) return true;
        return false;
    }

    @Override
    public Boolean orderFinsh(String orderId){
        if (orderId == null){
            log.info("【订单详情】，参数异常，buyerId={}", orderId);
            throw new OrderException(OrderEnum.ORDER_PARAM_ERROR);
        }
        int finish = orderMasterMapper.finish(orderId);
        if(finish == 1) return true;
        return false;
    }

    @Override
    public Boolean orderPay(String buyerId, String orderId) {
        if (buyerId == null){
            log.info("【订单详情】，参数异常，buyerId={}", buyerId);
            throw new OrderException(OrderEnum.ORDER_PARAM_ERROR);
        }
        if (orderId == null){
            log.info("【订单详情】，参数异常，buyerId={}", orderId);
            throw new OrderException(OrderEnum.ORDER_PARAM_ERROR);
        }
        int pay = this.orderMasterMapper.pay(buyerId, orderId);
        if(pay == 1) return true;
        return false;
    }

    @Override
    public PageVO<List<OrderMaster>> getOrderByPage(Integer page, Integer size) {
        Page<OrderMaster> condition = new Page<>(page,size);
        Page<OrderMaster> result = this.orderMasterMapper.selectPage(condition, null);
        PageVO<List<OrderMaster>> pageVO = new PageVO<>();
        pageVO.setContent(result.getRecords());
        pageVO.setSize(result.getSize());
        pageVO.setTotal(result.getTotal());
        return pageVO;
    }

    @Override
    public boolean handler(String type, String orderId) {
        OrderMaster orderMaster = this.orderMasterMapper.selectById(orderId);
        if(orderMaster.getOrderStatus() != 0){
            log.info("【处理订单】，订单状态异常，orderMaster={}", orderMaster);
            throw new OrderException(OrderEnum.ORDER_STATUS_ERROR);
        }
        int row = 0;
        switch (type){
            case "cancel":
                row = this.orderMasterMapper.handler(2, orderId);
                break;
            case "finish":
                row = this.orderMasterMapper.handler(1, orderId);
                break;
        }
        if(row == 1) return true;
        return false;
    }

}
