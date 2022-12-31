package com.study.v1.product.controller;

import com.study.domain.entity.ProductDiscount;
import com.study.v1.product.dto.HomeProductDto;
import com.study.v1.product.service.HomeProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product/home")
@AllArgsConstructor
public class HomeProductController {

    private final HomeProductService homeProductService;

    @GetMapping("/discount/{type}")
    public ResponseEntity<Slice<HomeProductDto>> getDiscountProducts(@PathVariable("type") ProductDiscount.Type type,
                                                                     @PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {
//        Slice<HomeProductDto> bestProducts = homeProductService.findDiscountProducts_1(type, pageable);
        Slice<HomeProductDto> bestProducts = homeProductService.findDiscountProducts_2(type, pageable);
        return ResponseEntity.ok(bestProducts);
    }
}
