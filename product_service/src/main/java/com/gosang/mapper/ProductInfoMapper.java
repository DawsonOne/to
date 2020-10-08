package com.gosang.mapper;

import com.gosang.entity.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author gosang
 * @since 2020-09-24
 */
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    int updateStatus(Integer id,Integer status);
}
