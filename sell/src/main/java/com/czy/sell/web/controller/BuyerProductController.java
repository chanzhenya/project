package com.czy.sell.web.controller;

import com.czy.sell.web.value.ProductInfoVo;
import com.czy.sell.web.value.ProductVo;
import com.czy.sell.web.value.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @PostMapping("/list")
    public ResultVo list() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("Success");

        ProductVo productVo = new ProductVo();
        ProductInfoVo productInfoVo = new ProductInfoVo();
        productVo.setProductInfoVos(Arrays.asList(productInfoVo));
        resultVo.setData(Arrays.asList(productVo));

        return resultVo;
    }
}
