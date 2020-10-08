package com.gosang.service;

import com.gosang.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gosang.vo.PageVO;
import com.gosang.vo.ProductInfoVO;
import com.gosang.vo.ProductManageVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author gosang
 * @since 2020-09-24
 */
public interface ProductInfoService extends IService<ProductInfo> {
    boolean subStock(Integer id,Integer quantity);
    PageVO<List<ProductManageVO>> findAllProduct(Integer page,  Integer size);
    ProductInfoVO findById(Integer id);
    Boolean deleteById(Integer id);
    Boolean updateStatus(Integer id,Boolean status);
    boolean updateProductManageVO(ProductManageVO productManageVO);
}
