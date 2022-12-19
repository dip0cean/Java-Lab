package com.study.domain.entity;

import com.study.domain.enumerate.DiscountType;
import com.study.domain.enumerate.Platform;
import com.study.domain.enumerate.VoucherType;
import com.study.util.converter.EnumListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", columnDefinition = "char")
    private String id;

    @Column(name = "num", updatable = false, columnDefinition = "bigint")
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

    @Column(name = "main_image_url", columnDefinition = "varchar")
    private String mainImageUrl; // 메인이미지

    //    @Convert(converter = ArrayConverter.class)
    @Column(name = "sub_image_urls", columnDefinition = "text")
    private String subImageUrls; // 서브이미지배열

    @Column(name = "code", columnDefinition = "varchar")
    private String code; // 제품코드

    @Column(name = "more_url", columnDefinition = "varchar")
    private String moreUrl; // 상세더보기 URL

    @Enumerated(EnumType.STRING)
    @Column(name = "voucher_type", columnDefinition = "varchar")
    private VoucherType voucherType; //바우처타입

    // 배열로 받아오면 search 에서 쿼리짜기가 거의 불가능해서 list 로 converting 함.
    // 라벨리스트 (커밍순(WAREHOUSING), 스폐셜(SPECIALIZED), 기간한정(TIME_SALES), 매진(SOLD_OUT), 빅딜(BIG_DEAL), 베스트(BEST))
    @Convert(converter = Label.EnumConverter.class)
    @Column(name = "label", columnDefinition = "varchar")
    private EnumSet<Label> label;

    @Column(name = "dealings_company_id", columnDefinition = "char")
    private String dealingsCompanyId;

    @Column(name = "sales_company_id", columnDefinition = "char")
    private String salesCompanyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type", columnDefinition = "varchar")
    private TaxType taxType = TaxType.TAX; //세금타입

    @Column(name = "tag_price", columnDefinition = "int")
    private Integer tagPrice; //태그가(대표 표시용)

    @Column(name = "web_price", columnDefinition = "int")
    private Integer webPrice; //웹 판매가격(대표 표시용)

    @Column(name = "app_price", columnDefinition = "int")
    private Integer appPrice; //앱 판매가격(대표 표시용)

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", columnDefinition = "varchar")
    private DiscountType discountType; //할인타입

    @Column(name = "discount_value", columnDefinition = "varchar")
    private String discountValue; //할인값

    @Column(name = "company_bunch_group_id", columnDefinition = "char")
    private String companyBunchGroupId; //묶음배송 id

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar")
    private Status status; //상태

    @Column(name = "review_count", columnDefinition = "int")
    private Integer reviewCount = 0; //전체 리뷰 개수

    @Column(name = "review_score_avg", columnDefinition = "varchar")
    private String reviewScoreAvg = "0.0"; // 리뷰 별점 평균 (별점항목평균의 총합 / 전체리뷰개수)

    //    @Convert(converter = ArrayConverter.class)
    @Column(name = "icon_urls", columnDefinition = "text")
    private String iconUrls; //아이콘

    //    @Convert(converter = ArrayConverter.class)
    @Column(name = "additional_tags", columnDefinition = "varchar")
    private String additionalTags;   // 추가특성정보

    @Column(name = "manufacturer", columnDefinition = "varchar")
    private String manufacturer;    // 제조사

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "varchar")
    private Type type = Type.GENERAL; // 제품 타입

    @Column(name = "sales_count", columnDefinition = "int")
    private Integer salesCount = 0; //판매개수 (스토어 제품 판매순 정렬용)

    @CreatedDate
    @Column(name = "registration_date", columnDefinition = "timestamp", updatable = false, insertable = false)
    private LocalDateTime registrationDate; //등록일시

    @Column(name = "new_date", columnDefinition = "timestamp")
    private LocalDateTime newDate; //진짜 신규저장일시 (최초로 노출로 변경된 일시)

    @Column(name = "event_tag", columnDefinition = "varchar")
    private String eventTag; //이벤트 태그

    @Column(name = "commission_rate", columnDefinition = "varchar")
    private String commissionRate; //수수료율

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_platform", columnDefinition = "varchar")
    private Platform salesPlatform; //제품 판매할 플랫폼 (웹/앱)

    @Column(name = "max_cart_quantity", columnDefinition = "int")
    private Integer maxCartQuantity;    // 담을 수 있는 최대 수량

    @Getter
    @AllArgsConstructor
    public enum Label {
        WAREHOUSING("enumProducttemplateLabelWarehousingDesc", "커밍순"), //커밍순
        SPECIALIZED("enumProducttemplateLabelSpecializedDesc", "스페셜"), //스페셜
        TIME_SALES("enumProducttemplateLabelTimesalesDesc", "기간한정"), //기간한정
        SOLD_OUT("enumProducttemplateLabelSoldoutDesc", "매진"), //매진
        BIG_DEAL("enumProducttemplateLabelBigdealDesc", "빅딜"), //빅딜
        BEST("enumProducttemplateLabelBestDesc", "베스트"); //베스트

        private final String desc;
        private final String ko;

        @Converter
        public static class EnumConverter extends EnumListConverter<Label> {
            public EnumConverter() {
                super(Label.class);
            }
        }

        public static Label findLabelByKo(String ko) {
            return Arrays.stream(values())
                    .filter(label -> label.ko.equals(ko))
                    .findFirst()
                    .orElse(null);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum TaxType {
        TAX("과제"), //과세
        EXEMPTION("면세"); //면세

        private final String ko;
    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        GENERAL("일반 제품"), // 일반제품
        PREGNANCY_TESTER("임신 테스터"); // 임신테스터

        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        SHOW("표시"),
        HIDE("숨김"),
        DELETED("삭제"),
        TEMP_SAVE("임시저장");

        private final String desc;
    }
}
