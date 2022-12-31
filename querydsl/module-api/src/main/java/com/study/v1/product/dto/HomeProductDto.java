package com.study.v1.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.domain.entity.Product;
import com.study.domain.enumerate.PlatformType;
import com.study.util.ObjectUtils;
import com.study.util.PricingUtils;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Data
public class HomeProductDto {
    private String id; //productId
    private String name; //제품명
    private Integer tagPrice; // 태그가 (대표 표시용)
    private Integer webPrice; //웹 판매가격(대표 표시용)
    private Integer appPrice; //웹 판매가격(대표 표시용)
    private int webDiscountRate; //할인값 (단위 %) *
    private int appDiscountRate; //할인값 (단위 %) *
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm a", locale = "en")
    private LocalDateTime endDate; // 만료일 (타임세일일때만 값이 있고, 그 외에는 null) *
    private PlatformType salesPlatform; //제품 판매할 플랫폼 (웹/앱)

    public HomeProductDto(Product product) {
        // 기존 데이터 복사
        ObjectUtils.copyProperties(product, this);

        // 옵션들 중 가장 낮은 가격
        this.webPrice = product.getWebPrice();

        // 옵션들 중 가장 낮은 가격
        this.appPrice = product.getAppPrice();

        int tagPrice = product.getLowestTagPrice();

        // 정상가 / 웹가격  앱가격 중 가장 비싼 가격으로 책정
        this.tagPrice = IntStream
                .of(tagPrice, this.webPrice, this.appPrice)
                .reduce(Math::max)
                .orElse(tagPrice);

        // 타임세일이 존재하는 경우 가장 오래 남은 종료일자로 세팅
        this.endDate = product.getLatestEndDate();

        // 웹 가격 할인율
        this.webDiscountRate = PricingUtils.calculateDiscountRate(this.tagPrice, this.webPrice);
        // 앱 가격 할인율
        this.appDiscountRate = PricingUtils.calculateDiscountRate(this.tagPrice, this.appPrice);
    }

    public static Slice<HomeProductDto> entitiesToDtoList(
            List<Product> sourceList,
            Pageable pageable,
            boolean hasNext,
            boolean isApp) {
        List<HomeProductDto> products = sourceList
                .stream()
                .filter(product -> product.getStatus().isShow())
                .map(HomeProductDto::new)
                .filter(dto -> isApp
                        ? dto.getSalesPlatform().isApp()
                        : dto.getSalesPlatform().isWeb())
                .toList();

        return new SliceImpl<>(products, pageable, hasNext);
    }
}
