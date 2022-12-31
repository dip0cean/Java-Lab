package com.study.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.util.PricingUtils;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_option")
public class ProductOption {

    @Id
    @Column(name = "id", columnDefinition = "charo")
    private String id;

    @Column(name = "num", columnDefinition = "bigint", updatable = false)
    private String num; //old_product_item_id;

    @Column(name = "product_id", columnDefinition = "char")
    private String productId;

    @Column(name = "name", columnDefinition = "varchar")
    private String name; // 옵션이름

    @Column(name = "code", columnDefinition = "code")
    private String code; // 옵션코드

    @Column(name = "sub_name", columnDefinition = "varchar")
    private String subName; // 서브명

    @Builder.Default
    @Column(name = "order_number", columnDefinition = "tinyint")
    private Integer orderNumber = -1; // 정렬

    @Builder.Default
    @Column(name = "web_price", columnDefinition = "int")
    private Integer webPrice = 0; // 웹판매가격

    @Builder.Default
    @Column(name = "app_price", columnDefinition = "int")
    private Integer appPrice = 0; // 앱판매가격

    @Builder.Default
    @Column(name = "fixed_price", columnDefinition = "int")
    private Integer fixedPrice = 0; // 정가

    @Builder.Default
    @Column(name = "total_stock", columnDefinition = "int")
    private Integer totalStock = 0; // 전체재고량

    @Builder.Default
    @Column(name = "leftover_stock", columnDefinition = "int")
    private Integer leftoverStock = 0; // 남은재고량

    @Column(name = "search_keyword", columnDefinition = "varchar")
    private String searchKeyword; // 검색키워드

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar")
    private Status status = Status.HIDE; // 상태

    @Column(name = "supply_price", columnDefinition = "varchar")
    private String supplyPrice; //공급가

    @Column(name = "company_management_code", columnDefinition = "varchar")
    private String companyManagementCode; //거래처제품옵션코드

    @Column(name = "shelf_life", columnDefinition = "date")
    private LocalDate shelfLife; // 유통기한

    @CreatedDate
    @Column(name = "registration_date", columnDefinition = "timestamp", updatable = false, insertable = false)
    private LocalDateTime registrationDate; // 등록일시

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product; // 제품기본정보

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "productOption")
    private List<ProductDiscount> productDiscounts = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    public enum Status {
        SHOW("enumProductOptionStatusShowDesc", "enumProductOptionStatusShowBtn"),
        HIDE("enumProductOptionStatusHideDesc", "enumProductOptionStatusHideBtn"),
        DELETED("enumProductOptionStatusDeletedDesc", "");

        private final String desc;
        private final String btn;
    }

    /**
     * 핫딜 / 타임세일 적용 시 웹 가격 계산
     *
     * @return 핫딜 / 타임세일 적용한 웹 가격
     */
    public int getWebPriceFromProductDiscount() {
        if (this.productDiscounts != null && this.productDiscounts.isEmpty()) {
            return this.webPrice;
        }
        int calcWebPrice =
                PricingUtils.getDiscountAmount(this.productDiscounts, this.webPrice, false);
        return Math.subtractExact(this.webPrice, calcWebPrice);
    }

    /**
     * 핫딜 / 타임세일 적용 시 앱 가격 계산
     *
     * @return 핫딜 / 타임세일 적용한 앱 가격
     */
    public int getAppPriceFromProductDiscount() {
        if (this.productDiscounts != null && this.productDiscounts.isEmpty()) {
            return this.appPrice;
        }
        int calcWebPrice =
                PricingUtils.getDiscountAmount(this.productDiscounts, this.appPrice, true);
        return Math.subtractExact(this.appPrice, calcWebPrice);
    }

    /**
     * 타임세일할 때 가장 미래인 마감일을 계산하는 메서드
     *
     * @return 현재 가장 미래의 마감일
     */
    public LocalDateTime getDiscountEndDate() {
        // 핫딜 / 타임세일 정렬
        if (this.productDiscounts == null || this.productDiscounts.isEmpty()) {
            return null;
        }

        this.productDiscounts.sort(Comparator.comparing(ProductDiscount::getEndDate).reversed());
        ProductDiscount productDiscount = productDiscounts.get(0);
        ProductDiscount.Type type = productDiscount.getType();

        if (type != ProductDiscount.Type.TIME_SALE) {
            return null;
        }

        return productDiscount.getEndDate();
    }
}
