package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PaymentTest {
    @Test
    @DisplayName("지불 금액을 파라미터로 Payment 객체가 생성되어야 한다")
    void create() {
        // given
        final String money = "1000";

        // when and then
        assertThat(new Payment(money)).isInstanceOf(Payment.class);
    }

    @Test
    @DisplayName("지불 금액이 숫자가 아니면 IllegalArgumentException을 발생시킨다")
    void when_money_is_not_a_number_should_throw_IllegalArgumentException() {
        // given
        final String money = "money";

        // when and then
        assertThatThrownBy(() -> new Payment(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1000", "900", "1900"})
    @DisplayName("지불 금액이 1000원 단위가 아니면 IllegalArgumentException을 발생시킨다")
    void when_amount_of_money_is_invalid_should_throw_IllegalArgumentException(final String money) {
        // when and then
        assertThatThrownBy(() -> new Payment(money))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
