package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gosang.entity.ProductCategory;
import com.gosang.entity.ProductInfo;
import com.gosang.enums.ProductEnum;
import com.gosang.exception.ProductException;
import com.gosang.mapper.ProductCategoryMapper;
import com.gosang.mapper.ProductInfoMapper;
import com.gosang.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gosang.util.EntityToVO;
import com.gosang.vo.PageVO;
import com.gosang.vo.ProductInfoVO;
import com.gosang.vo.ProductManageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author gosang
 * @since 2020-09-24
 */
@Service
@Slf4j
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;
    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public boolean subStock(Integer id, Integer quantity) {
        ProductInfo productInfo = productInfoMapper.selectById(id);
        Integer result = productInfo.getProductStock() - quantity;
        if (result < 0) {
            log.info("【修改库存】库存数量异常！productInfo={}", productInfo);
            throw new ProductException(ProductEnum.PRODUCT_STOCK_ERROR);
        }
        productInfo.setProductStock(result);
        int i = productInfoMapper.updateById(productInfo);
        if (i == 1) return true;
        return false;
    }

    @Override
    public PageVO<List<ProductManageVO>> findAllProduct(Integer page, Integer size) {
        Page<ProductInfo> productInfoPage = new Page<>(page, size);
        Page<ProductInfo> productInfos = productInfoMapper.selectPage(productInfoPage, null);
        List<ProductInfo> records = productInfos.getRecords();
        List<ProductManageVO> productManageVOList = new ArrayList();
        ProductManageVO productManageVO;
        for (ProductInfo record : records) {
            productManageVO = new ProductManageVO();
            BeanUtils.copyProperties(record, productManageVO);
            String categoryName = productCategoryMapper.findCategoryName(record.getCategoryType());
            productManageVO.setCategoryName(categoryName);
            if (record.getProductStatus() == 1) {
                // 1正常0下架
                productManageVO.setStatus(true);
            } else {
                productManageVO.setStatus(false);
            }
            productManageVOList.add(productManageVO);
        }
        PageVO<List<ProductManageVO>> pageVO = new PageVO();
        pageVO.setTotal(productInfos.getTotal());
        pageVO.setSize(productInfos.getSize());
        pageVO.setContent(productManageVOList);
        return pageVO;
    }

    @Override
    public ProductInfoVO findById(Integer id) {
        ProductInfo productInfo = productInfoMapper.selectById(id);
        ProductCategory productCategory = new ProductCategory();
        ProductInfoVO productInfoVO = EntityToVO.ProductInfoToProductInfoVO(productInfo);
        productCategory.setCategoryType(productInfo.getCategoryType());
        productInfoVO.setCategory(productCategory);
        return productInfoVO;
    }

    @Override
    public Boolean deleteById(Integer id) {
        int i = productInfoMapper.deleteById(id);
        if (i == 1) return true;
        return false;
    }

    @Override
    public Boolean updateStatus(Integer id, Boolean status) {
        Integer sta = 0;
        if (status) sta = 1;
        int i = productInfoMapper.updateStatus(id, sta);
        if (i == 1) return true;
        return false;
    }

    @Override
    public boolean updateProductManageVO(ProductManageVO productManageVO) {
        ProductInfo productInfo = this.productInfoMapper.selectById(productManageVO.getProductId());
        BeanUtils.copyProperties(productManageVO, productInfo);
        productInfo.setCategoryType(productManageVO.getCategory().getCategoryType());
        if(productManageVO.getStatus()) productInfo.setProductStatus(1);
        else productInfo.setProductStatus(0);
        int row = this.productInfoMapper.updateById(productInfo);
        if(row == 1) return true;
        return false;
    }
}
