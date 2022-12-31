package com.study.util;

import com.study.domain.entity.ProductDiscount;
import com.study.domain.enumerate.DiscountType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class PricingUtils {

    /**
     * 할인 금액 산정하기
     *
     * @param discountType 할인 타입 (금액 할인 혹은 퍼센트 할인)
     * @param func         할인 대상 금액 산정 함수(calDiscountAmountForMoneyType 혹은 identityDiscountAmount)
     * @return 최종 할인 금액 리턴
     */
    public static BinaryOperator<Integer> calculateByAmount(
            final DiscountType discountType,
            final IntBinaryOperator func
    ) {
        return (totalPayment, discountAmount) -> {
            // 퍼센트 할인
            if (discountType.equals(DiscountType.PERCENT)) {
                return new BigDecimal(totalPayment)
                        .multiply(new BigDecimal(discountAmount).multiply(new BigDecimal("0.01")))
                        .setScale(0, RoundingMode.HALF_EVEN)
                        .intValue();
            }

            // 금액 할인
            return func.applyAsInt(totalPayment, discountAmount);
        };
    }

    /**
     * 금액 할인 분배 시, 옵션 결제 비율만큼 금액 할인 나누기 할인 금액 * (옵션 금액 / 전체 금액)
     *
     * @param optionTargetAmount 옵션 금액
     * @return 옵션 금액만큼 분배된 할인 금액 리턴
     */
    public static IntBinaryOperator distributeByAmount(final Integer optionTargetAmount) {
        return (targetAmount, discountAmount) -> {
            // 20% -> 0.2, 1% -> 0.01 로 치환
            BigDecimal percent = new BigDecimal(optionTargetAmount)
                    .divide(new BigDecimal(targetAmount), 2, RoundingMode.HALF_EVEN);

            return new BigDecimal(discountAmount)
                    .multiply(percent)
                    .setScale(0, RoundingMode.HALF_EVEN).intValue();
        };
    }

    /**
     * 금액 할인 그대로 리턴 targetAmount 할인 대상 금액 discountAmount 할인 금액
     *
     * @return 할인 금액
     */
    public static IntBinaryOperator identityAmount() {
        return (totalPayment, discountAmount) -> discountAmount;
    }

    /**
     * 지급 예정 적립금 계산 (구매 금액의 1% 지급) targetAmount 최종 결제 금액 ex. 1,000 원의 1% 적립금 계산식 > 1111 * 0.01 = 11
     * (소수점 첫 번째 자리에서 반올림)
     *
     * @return targetAmount 의 1%
     */
    public static IntUnaryOperator calculateByRewordMtPoint() {
        return totalPayment -> {
            // 결제 금액이 0원인 경우 0 리턴
            if (totalPayment == 0) {
                return 0;
            }

            return new BigDecimal(totalPayment)
                    .multiply(new BigDecimal("0.01"))
                    .setScale(0, RoundingMode.HALF_EVEN)
                    .intValue();
        };
    }

    /**
     * 쿠폰 적용 시, 할인 금액 산정 메소드 > 쿠폰 관련 VO 를 이용해서 사용하는 경우 (VO 객체들 추상화 필요)
     *
     * @param discountType      // 할인 타입 (MONEY / PERCENT)
     * @param totalPayment      // 제품 금액
     * @param maxDiscountAmount // 해당 쿠폰의 최대 할인 금액
     * @param couponAmount      // 쿠폰의 할인 금액
     * @return 할인 적용 금액
     */
    public static Integer calculateByDiscountType(DiscountType discountType,
                                                  int totalPayment,
                                                  int maxDiscountAmount,
                                                  int couponAmount) {
        // 할인 예상 금액 산정 결과
        int finalDiscountAmount = calculateByAmount(discountType, identityAmount())
                .andThen(discountAmount -> {
                    if (maxDiscountAmount > 0) {
                        return discountAmount >= maxDiscountAmount ? maxDiscountAmount : discountAmount;
                    }

                    return discountAmount;
                })
                .apply(totalPayment, couponAmount);

        // 만약 결제 예정 금액이 할인 금액보다 작다면 최종 할인 예정 금액을 결제 예정 금액으로 리턴
        // ex. 할인 예정 금액 500원, 결제 예정 금액 300원 > 최종 할인 금액 300원, 결제 예정 금액 0원이 된다.
        return Math.min(finalDiscountAmount, totalPayment);

    }

    /**
     * 할인율 계산하는 메서드 (tagPrice -price) * 100 / tagPrice
     *
     * @param tagPrice 태그가
     * @param price    웹가격
     * @return 할인율
     */
    public static int calculateDiscountRate(int tagPrice, int price) {
        BigDecimal priceGap = new BigDecimal(
                Math.max(tagPrice - price, 0)); // tag가 - price는 0이상이어야 한다.
        return priceGap.multiply(new BigDecimal(100))
                .divide(new BigDecimal(Math.max(tagPrice, 1)), 0, RoundingMode.HALF_UP).intValue();
    }

    /**
     * 할인양 계산하는 메서드
     *
     * @param productDiscounts 제품옵션에 걸린 할인정보들
     * @param originPrice      //원래 (웹/앱) 가격
     * @return 할인된 금액
     */
    public static Integer getDiscountAmount(List<ProductDiscount> productDiscounts,
                                            Integer originPrice, boolean isApp) {
        Integer discountAmount = null; //할인양
        if (productDiscounts != null && productDiscounts.size() > 0) { //타임세일이 맨 위에 있다.
            productDiscounts.sort(
                    Comparator.comparing(pd -> pd.getType() == ProductDiscount.Type.TIME_SALE ? 1 : 2));
            ProductDiscount productDiscount = productDiscounts.get(0);
            IntBinaryOperator func = PricingUtils.identityAmount();

            // 할인 타입
            DiscountType discountType = productDiscount.getDiscountType();
            // 할인율(PERCENT) / 할인 금액(MONEY)
            int amount = productDiscount.getAmount();
            // 접근한 플랫폼이 App 인 경우 && 앱 할인이 존재하는 경우
            if (isApp && productDiscount.getAppAmount() > 0) {
                discountType = productDiscount.getAppDiscountType();
                amount = productDiscount.getAppAmount();
            }
            // 할인 금액 산정
            discountAmount = PricingUtils.calculateByAmount(discountType, func)
                    .apply(originPrice, amount);
        }
        return discountAmount == null ? 0 : discountAmount;
    }
}
