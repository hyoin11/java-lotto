import lotto.domain.LottoMatchInfo;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("로또 테스트")
public class LottoNumberTest {
    @Test
    @DisplayName("6개의 번호를 가진 로또를 생성할 수 있다.")
    void generateSixLottoNumbers_test() {
        LottoNumber lottoNumber = new LottoNumber();

        Assertions.assertThat(lottoNumber.getNumbers()).hasSize(6);
    }

    @RepeatedTest(10)
    @DisplayName("매번 다른 번호를 가진 로또를 생성할 수 있다.")
    void generateRandomLottoNumbers_test() {
        LottoNumber lottoNumber1 = new LottoNumber();
        LottoNumber lottoNumber2 = new LottoNumber();

        Assertions.assertThat(lottoNumber1).isNotEqualTo(lottoNumber2);
    }

    @ParameterizedTest
    @DisplayName("로또 번호가 정해진 사이값의 숫자가 아니라면 예외를 던진다.")
    @ValueSource(ints = {-1, 46})
    void validateLottoNumber_test(int lottoNumber) {
        assertThatThrownBy(() -> InputView.validateLottoNumberInRange(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("당첨 번호의 입력값이 null이거나 빈 공백인 경우 예외를 던진다.")
    void validateWinningNumbersNullAndEmpty_test(String input) {
        assertThatThrownBy(() -> InputView.validateInputWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1% 2% 3% 4% 5% 6", "a, b, c, d, e, f"})
    @DisplayName("당첨번호의 입력값이 공백의 유무와 상관없이 콤마(,)와 숫자의 조합이 아니라면 예외를 던진다.")
    void validateNumberCommaExpression_test(String input) {
        assertThatThrownBy(() -> InputView.validateInputWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호의 입력값의 숫자가 6개가 아니라면 예외를 던진다.")
    void validateSixWinningNumber_test() {
        String input = "1, 2, 3, 4, 5, 6, 7";
        assertThatThrownBy(() -> InputView.validateInputWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에서 숫자만 분리할 수 있다.")
    void extractWinningNumbers_test() {
        String input = "1, 2, 3, 4, 5, 6";
        Integer[] numbers = {1, 2, 3, 4, 5, 6};

        List<Integer> extractNumbers = InputView.extractNumbers(input);

        Assertions.assertThat(extractNumbers).contains(numbers);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6})
    @DisplayName("당첨 번호와 일치하는 숫자에 따라 순위를 매길 수 있다.")
    void setRank_test(int matchCount) {
        Rank rank = Rank.valueOf(matchCount, false);

        Assertions.assertThat(matchCount).isEqualTo(rank.getCountOfMatch());
    }

    @Test
    @DisplayName("당첨 번호가 0~6개 안의 숫자에서 확인되는 지 확인할 수 있다.")
    void checkMatchingNumbers_test() {
        LottoNumber lottoNumber = new LottoNumber();
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        LottoMatchInfo matchInfo = LottoMatchInfo.countMatchingNumbers(lottoNumber, winningNumbers);

        Assertions.assertThat(matchInfo.getMatchCount()).isBetween(0, 6);
    }

    @Test
    @DisplayName("보너스 번호가 없을 때 수익률 계산이 동작하는지 확인할 수 있다.")
    void calculateReturnRate_test() {
        int purchaseAmount = 10_000;
        LottoTicket lottoTicket = new LottoTicket(10);
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        LottoResult lottoResult = lottoTicket.calculateResult(winningNumbers);
        double returnRate = OutputView.calculateReturnRate(purchaseAmount, lottoResult);

        Assertions.assertThat(returnRate).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("보너스 번호가 당첨번호에 포함되어 있을 때 예외를 던진다.")
    void validateBonusNumber_test() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

        assertThatThrownBy(() -> InputView.validateBonusNumber(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호가 있을 때 수익률 계산이 동작하는지 확인할 수 있다.")
    void calculateWithBonusNumberReturnRate_test() {
        int purchaseAmount = 10_000;
        LottoTicket lottoTicket = new LottoTicket(10);
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoResult lottoResult = lottoTicket.calculateResult(winningNumbers, bonusNumber);
        double returnRate = OutputView.calculateReturnRate(purchaseAmount, lottoResult);

        Assertions.assertThat(returnRate).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 숫자와 보너스 번호 일치 여부를 확인할 수 있다.")
    void checkMatchingNumbersAndBonus_test() {
        LottoNumber lottoNumber = new LottoNumber();
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoMatchInfo lottoMatchInfo = LottoMatchInfo.countMatchingNumbers(lottoNumber, winningNumbers, bonusNumber);

        Assertions.assertThat(lottoMatchInfo.getMatchCount()).isBetween(0, 6);
        Assertions.assertThat(lottoMatchInfo.isBonusMatch()).isIn(true, false);
    }

}