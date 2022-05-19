package lotto.domain;

import static lotto.domain.LottoCondition.TICKET_COST;

import java.util.Objects;
import lotto.service.StringToPaymentConverter;

public class LottoPayment {
    private final int money;
    private final int purchasableAmount;

    LottoPayment(final int money, final int purchasableAmount) {
        this.money = money;
        this.purchasableAmount = purchasableAmount;
    }

    public static LottoPayment convertAndCreate(final String moneyString) {
        final int money = StringToPaymentConverter.convert(moneyString);
        return new LottoPayment(money, money / TICKET_COST.getCondition());
    }

    public int getMoney() {
        return money;
    }

    public int getPurchasableAmount() {
        return purchasableAmount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "money=" + money +
                ", purchasableAmount=" + purchasableAmount +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LottoPayment lottoPayment = (LottoPayment) o;
        return money == lottoPayment.money && purchasableAmount == lottoPayment.purchasableAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, purchasableAmount);
    }
}
