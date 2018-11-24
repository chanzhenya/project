package com.czy.sell.service;

import com.czy.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public ProductInfo findOne(String productId);

    /**
     * 查询所有在架的商品列表
     * @return
     */
    public List<ProductInfo> findUpAll();

    public Page<ProductInfo> findAll(Pageable pageable);

    public ProductInfo save(ProductInfo productInfo);

    //加库存

    //减库存
}
