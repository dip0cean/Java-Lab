package com.study.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.domain.enumerate.DiscountType;
import com.study.domain.enumerate.PlatformType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OrderBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id", columnDefinition = "char")
    private String id;

    @Column(name = "num", columnDefinition = "bigint", updatable = false)
    private String num; // 순번

    @Column(name = "product_review_score_group_id", columnDefinition = "char")
    private String productReviewScoreGroupId;

    @Column(name = "product_category_large_id", columnDefinition = "char")
    private String productCategoryLargeId;

    @Column(name = "product_category_medium_id", columnDefinition = "char")
    private String productCategoryMediumId;

    @Column(name = "product_category_small_id", columnDefinition = "char")
    private String productCategorySmallId;

    @Column(name = "name", columnDefinition = "varchar")
    private String name; // 제품명

    @Column(name = "title", columnDefinition = "varchar")
    private String title; // 제품설명제목

    @Column(name = "content", columnDefinition = "text")
    private String content; // 제품설명내용

    @Column(name = "tag_price", columnDefinition = "int")
    private Integer tagPrice; // 태그가(대표 표시용)

    @Column(name = "web_price", columnDefinition = "int")
    private Integer webPrice; // 웹 판매가격(대표 표시용)

    @Column(name = "app_price", columnDefinition = "int")
    private Integer appPrice; // 앱 판매가격(대표 표시용)

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", columnDefinition = "varchar")
    private DiscountType discountType; // 할인타입

    @Column(name = "discount_value", columnDefinition = "int")
    private String discountValue; // 할인값

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar")
    private Status status; // 상태

    @CreatedDate
    @Column(name = "registration_date", columnDefinition = "timestamp", updatable = false, insertable = false)
    private LocalDateTime registrationDate; // 등록일시

    @Column(name = "new_date", columnDefinition = "timestamp")
    private LocalDateTime newDate; // 진짜 신규저장일시 (최초로 노출로 변경된 일시)

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_platform", columnDefinition = "varchar")
    private PlatformType salesPlatform; // 제품 판매할 플랫폼 (웹/앱)

    @Column(name = "max_cart_quantity", columnDefinition = "int")
    private Integer maxCartQuantity;    // 담을 수 있는 최대 수량

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    @OrderBy(clause = "orderNumber desc")
    private List<ProductOption> productOptions = new ArrayList<>();

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductDiscount> productDiscounts = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    public enum Status {
        SHOW("enumProductStatusShowDesc", "enumProductStatusShowBtn"),
        HIDE("enumProductStatusHideDesc", "enumProductStatusHideBtn"),
        DELETED("enumProductStatusDeletedDesc", ""),
        TEMP_SAVE("enumProductStatusTempsaveDesc", "");

        private final String desc;
        private final String btn;

        public boolean isShow() {
            return this == SHOW;
        }
    }

    public List<ProductOption> getValidProductOptions() {
        return this.productOptions
                .stream()
                .filter(option -> option.getStatus() == ProductOption.Status.SHOW)
                .filter(option -> option.getWebPrice() != null)
                .filter(option -> option.getAppPrice() != null)
                .filter(option -> option.getFixedPrice() != null)
                .toList();
    }

    public int getLowestWebPrice() {
        return this.getValidProductOptions()
                .stream()
                .map(ProductOption::getWebPriceFromProductDiscount)
                .reduce(Math::min)
                .orElse(100);
    }

    public int getLowestAppPrice() {
        return this.getValidProductOptions()
                .stream()
                .map(ProductOption::getAppPriceFromProductDiscount)
                .reduce(Math::min)
                .orElse(100);
    }

    public int getLowestTagPrice() {
        return this.getValidProductOptions()
                .stream()
                .map(ProductOption::getFixedPrice)
                .reduce(Math::min)
                .orElse(100);
    }

    public int getHighestPrice() {
        int tagPrice = this.getLowestTagPrice();
        int webPrice = this.getLowestWebPrice();
        int appPrice = this.getLowestAppPrice();

        return IntStream
                .of(tagPrice, webPrice, appPrice)
                .reduce(Math::max)
                .orElse(tagPrice);
    }

    public LocalDateTime getLatestEndDate() {
        return this.getValidProductOptions()
                .stream()
                .map(ProductOption::getDiscountEndDate)
                .filter(Objects::nonNull)
                .reduce((prev, next) -> prev.isAfter(next) ? prev : next)
                .orElse(null);
    }
}
