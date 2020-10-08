package com.gosang.util;


import com.gosang.entity.ProductInfo;
import com.gosang.enums.StatusEnum;
import com.gosang.vo.ProductInfoVO;

/**
 * 实体类转VO
 */
public class EntityToVO {

    /**
     * ProductInfo 转 ProductInfoVO
     */
    public static ProductInfoVO ProductInfoToProductInfoVO(ProductInfo productInfo){
        ProductInfoVO result = new ProductInfoVO();
        result.setId(productInfo.getProductId());
        result.setDecription(productInfo.getProductDescription());
        result.setName(productInfo.getProductName());
        result.setIcon(productInfo.getProductIcon());
        result.setPrice(productInfo.getProductPrice());
        result.setStock(productInfo.getProductStock());
        if (productInfo.getProductStatus() == 1) result.setStatus(StatusEnum.STATUS_TRUE.getStatus());
        else result.setStatus(StatusEnum.STATUS_FALSE.getStatus());
        return result;
    }

}
