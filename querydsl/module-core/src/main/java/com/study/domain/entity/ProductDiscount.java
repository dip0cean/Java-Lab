package com.study.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.domain.enumerate.DiscountType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_discount")
public class ProductDiscount {

    @Id
    @Column(name = "id", columnDefinition = "char")
    private String id;

    @Column(name = "num", columnDefinition = "bigint")
    private String num;

    @Column(name = "product_id", columnDefinition = "char")
    private String productId;

    @Column(name = "product_option_id", columnDefinition = "char")
    private String productOptionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "varchar")
    private Type type;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", columnDefinition = "varchar")
    private DiscountType discountType = DiscountType.PERCENT;

    @Column(name = "amount", columnDefinition = "int")
    private int amount;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "app_discount_type", columnDefinition = "varchar")
    private DiscountType appDiscountType = DiscountType.PERCENT;

    @Column(name = "app_amount", columnDefinition = "int")
    private int appAmount;

    @Column(name = "start_date", columnDefinition = "timestamp")
    private LocalDateTime startDate;

    @Column(name = "end_date", columnDefinition = "timestamp")
    private LocalDateTime endDate;

    @Builder.Default
    @Column(name = "commission_rate", columnDefinition = "varchar")
    private String commissionRate = "0";

    @Builder.Default
    @Column(name = "app_commission_rate", columnDefinition = "varchar")
    private String appCommissionRate = "0";

    @Builder.Default
    @Column(name = "order_number", columnDefinition = "tinyint")
    private int orderNumber = -1;

    @Builder.Default
    @Column(name = "valid_flag", columnDefinition = "bit")
    private Boolean validFlag = true;

    @Column(name = "discount_id", columnDefinition = "bigint")
    private String discountId;

    @CreatedDate
    @Column(name = "registration_date", columnDefinition = "timestamp", updatable = false, insertable = false)
    private LocalDateTime registrationDate; //등록일시

    @Builder.Default
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product = Product.builder().build();

    @Builder.Default
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOption productOption = ProductOption.builder().build();

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        HOT_DEAL("enumProductdiscountTypeHotdealDesc", "핫딜"),
        TIME_SALE("enumProductdiscountTypeTimesaleDesc", "타임세일");

        private final String desc;
        private final String ko;

        public boolean isHotDeal() {
            return this == HOT_DEAL;
        }

        public boolean isTimeSale() {
            return this == TIME_SALE;
        }
    }
}
