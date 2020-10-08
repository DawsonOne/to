package com.gosang.service;

import com.gosang.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gosang.vo.ProductCategoryVO;
import com.gosang.vo.ProductInfoVO;
import com.gosang.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author gosang
 * @since 2020-09-24
 */
public interface ProductCategoryBuyerService extends IService<ProductCategory> {
    ResultVO<List<ProductCategoryVO>> ProductCategoryList();
    List<ProductCategoryVO> findAllProduct();
}
