package com.spring.solid.srp.payment;

import com.spring.solid.srp.company.bc.NewBcGlobalPaymentService;
import com.spring.solid.srp.company.bc.NewBcLocalPaymentService;
import com.spring.solid.srp.company.bc.OldBcPaymentService;
import com.spring.solid.srp.company.hana.NewHanaPaymentService;
import com.spring.solid.srp.company.hana.OldHanaPaymentService;
import com.spring.solid.srp.domain.CardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CardTypeFactory {

    // SRP 적용
    private final NewBcLocalPaymentService newBcLocalPaymentService;
    private final NewBcGlobalPaymentService newBcGlobalPaymentService;
    private final NewHanaPaymentService newHanaPaymentService;

    // SRP 미적용
    private final OldBcPaymentService oldBcPaymentService;
    private final OldHanaPaymentService oldHanaPaymentService;

    public OldPaymentService getOldPaymentService(CardDto.Request card) throws NoSuchMethodException {

        switch (card.getCardType()) {
            case BC:
                return oldBcPaymentService;
            case HANA:
                return oldHanaPaymentService;
            default:
                throw new NoSuchMethodException();
        }
    }

    public LocalPaymentService getLocalPaymentService(CardDto.Request card) throws NoSuchMethodException {

        switch (card.getCardType()) {
            case BC:
                return newBcLocalPaymentService;
            case HANA:
                return newHanaPaymentService;
            default:
                throw new NoSuchMethodException();
        }
    }

    public GlobalPaymentService globalPaymentService(CardDto.Request card) throws NoSuchMethodException {

        switch (card.getCardType()) {
            case BC:
                return newBcGlobalPaymentService;
            case HANA:
            default:
                throw new NoSuchMethodException();
        }
    }
}
