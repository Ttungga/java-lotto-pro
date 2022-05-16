package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoNumbersTest {
    private static final LottoNumbers winningNumbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));

    @Test
    @DisplayName("6개의 숫자 배열을 파라미터로 로또 번호가 생성되어야 한다")
    void create_by_number_list() {
        // given
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        final LottoNumbers lottoNumbers = new LottoNumbers(numbers);

        // then
        assertThat(lottoNumbers).isInstanceOf(LottoNumbers.class);
        assertThat(lottoNumbers).isEqualTo(new LottoNumbers(numbers));
    }

    @ParameterizedTest
    @MethodSource("lottoNumbersMatchingLessThanThreeNumbers")
    @DisplayName("주어진 번호가 3개 미만으로 일치하면 0이 반환되어야 한다")
    void when_no_matches_should_return_0(final LottoNumbers lottoNumbers) {
        // when and then
        assertThat(lottoNumbers.matches(winningNumbers)).isEqualTo(0);
    }

    @Test
    @DisplayName("주어진 번호가 3개 일치하면 3이 반환되어야 한다")
    void when_three_matches_should_return_3() {
        // given
        final LottoNumbers numbers = new LottoNumbers(Arrays.asList(2, 3, 6, 37, 41, 44));

        // when and then
        assertThat(numbers.matches(winningNumbers)).isEqualTo(3);
    }

    @Test
    @DisplayName("주어진 번호가 4개 일치하면 4가 반환되어야 한다")
    void when_four_matches_should_return_4() {
        // given
        final LottoNumbers numbers = new LottoNumbers(Arrays.asList(1, 3, 5, 6, 41, 44));

        // when and then
        assertThat(numbers.matches(winningNumbers)).isEqualTo(4);
    }

    @Test
    @DisplayName("주어진 번호가 5개 일치하면 5가 반환되어야 한다")
    void when_five_matches_should_return_5() {
        // given
        final LottoNumbers numbers = new LottoNumbers(Arrays.asList(1, 3, 4, 5, 6, 44));

        // when and then
        assertThat(numbers.matches(winningNumbers)).isEqualTo(5);
    }

    @Test
    @DisplayName("주어진 번호가 6개 일치하면 6이 반환되어야 한다")
    void when_six_matches_should_return_6() {
        // given
        final LottoNumbers numbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));

        // when and then
        assertThat(numbers.matches(winningNumbers)).isEqualTo(6);
    }

    private static Stream<Arguments> lottoNumbersMatchingLessThanThreeNumbers() {
        return Stream.of(Arguments.of(new LottoNumbers(Arrays.asList(13, 21, 34, 37, 41, 44))),
                Arguments.of(new LottoNumbers(Arrays.asList(3, 21, 34, 37, 41, 44))),
                Arguments.of(new LottoNumbers(Arrays.asList(2, 5, 34, 37, 41, 44))));
    }
}
