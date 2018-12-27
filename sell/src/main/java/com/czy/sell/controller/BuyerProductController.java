package com.czy.sell.controller;

import com.czy.sell.VO.ResultVo;
import com.czy.sell.entity.ProductCategory;
import com.czy.sell.entity.ProductInfo;
import com.czy.sell.service.CategoryService;
import com.czy.sell.service.ProductService;
import com.czy.sell.utils.ResultVoUtil;
import com.czy.sell.VO.ProductInfoVo;
import com.czy.sell.VO.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list() {
        //1、查询所有的上架商品
        List<ProductInfo> productInfos = productService.findUpAll();

        //2、查询类目（一次性查询）
        //Java8 lamda表达式，获取CategoryType
        List<Integer> categoryTypeList = productInfos.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3、数据封装
        List<ProductVo> productVos = new ArrayList<>();
        for(ProductCategory category:productCategories) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(category.getCategoryType());
            productVo.setCategoryName(category.getCategoryName());

            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for(ProductInfo info:productInfos) {
                if(info.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(info, productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVo.setProductInfoVos(productInfoVos);
            productVos.add(productVo);
        }

        return ResultVoUtil.success(productVos);
    }
}
