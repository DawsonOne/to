package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gosang.entity.ProductCategory;
import com.gosang.entity.ProductInfo;
import com.gosang.mapper.ProductCategoryMapper;
import com.gosang.mapper.ProductInfoMapper;
import com.gosang.service.ProductCategoryBuyerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gosang.util.EntityToVO;
import com.gosang.vo.ProductCategoryVO;
import com.gosang.vo.ProductInfoVO;
import com.gosang.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author gosang
 * @since 2020-09-24
 */
@Service
public class ProductCategoryBuyerServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryBuyerService {

    @Autowired
    public ProductCategoryMapper productCategoryMapper;
    @Autowired
    public ProductInfoMapper productInfoMapper;


    @Override
    public ResultVO<List<ProductCategoryVO>> ProductCategoryList() {
        List<ProductCategory> productCategories = productCategoryMapper.selectList(null);
        List<ProductCategoryVO> productCategoryVOList = new ArrayList();
        ProductCategoryVO productCategoryVO;
        for(ProductCategory procategory : productCategories){
            //创建商品种类
            productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setName(procategory.getCategoryName());
            productCategoryVO.setType(procategory.getCategoryType());
            //通过商品种类查找商品详情信息
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("category_type",productCategoryVO.getType());
            wrapper.eq("product_status",1);
            List<ProductInfo> proList = productInfoMapper.selectList(wrapper);
            //ProductInfo转成ProductInfoVO
            List<ProductInfoVO> productInfoVOList = new ArrayList();
            for (ProductInfo productInfo : proList) {
                ProductInfoVO productInfoVO = EntityToVO.ProductInfoToProductInfoVO(productInfo);
                productInfoVOList.add(productInfoVO);
            }
            productCategoryVO.setFoods(productInfoVOList);
            productCategoryVOList.add(productCategoryVO);
        }
        ResultVO<List<ProductCategoryVO>> resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(productCategoryVOList);

        return resultVO;
    }
    @Override
    public List<ProductCategoryVO> findAllProduct(){
        List<ProductCategory> productCategories = productCategoryMapper.selectList(null);
        List<ProductCategoryVO> productCategoryVOList = new ArrayList();
        ProductCategoryVO productCategoryVO;
        for(ProductCategory procategory : productCategories){
            //创建商品种类
            productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setName(procategory.getCategoryName());
            productCategoryVO.setType(procategory.getCategoryType());
            productCategoryVOList.add(productCategoryVO);
        }
        return productCategoryVOList;
    }
}
