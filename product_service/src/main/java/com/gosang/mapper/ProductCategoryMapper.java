package com.gosang.mapper;

import com.gosang.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author gosang
 * @since 2020-09-24
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
  String findCategoryName(Integer categoryId);
}
