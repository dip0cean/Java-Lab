package com.study.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_best")
public class ProductBest {

    @Id
    @Column(name = "id", columnDefinition = "char")
    private String id;

    @Column(name = "product_id", columnDefinition = "char")
    private String productId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "sales", columnDefinition = "int")
    private Integer   sales = 0; //판매개수

    @Column(name = "start_date", columnDefinition = "date")
    private LocalDate startDate; //기간 시작

    @Column(name = "end_date", columnDefinition = "date")
    private LocalDate endDate; //기간 종료
}
