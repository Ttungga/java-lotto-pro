package lotto.domain;

import static lotto.domain.message.ErrorMessage.INVALID_BONUS_BALL;

import java.util.Objects;
import lotto.service.BonusBallValidator;
import lotto.service.LottoNumberValidator;
import lotto.util.StringToIntegerConverter;

public class BonusBall {
    private final int number;

    public BonusBall(final int number) {
        LottoNumberValidator.checkRangeOfNumber(number, INVALID_BONUS_BALL);
        this.number = number;
    }

    public static BonusBall convertAndCreate(final String numberString, final LottoNumbers winningNumbers) {
        final int number = StringToIntegerConverter.parseInt(numberString, INVALID_BONUS_BALL);
        LottoNumberValidator.checkRangeOfNumber(number, INVALID_BONUS_BALL);
        final BonusBall bonusBall = new BonusBall(number);
        BonusBallValidator.validate(bonusBall, winningNumbers);
        return bonusBall;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "BonusBall{" +
                "number=" + number +
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
        final BonusBall bonusBall = (BonusBall) o;
        return number == bonusBall.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
