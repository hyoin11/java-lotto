package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottosTest {

    private WinningLottos winningLottos;
    @BeforeEach
    void setup() {
        winningLottos = new WinningLottos();
    }

    @Test
    void getMatchNumber() {
        WinningLottoType matchingNumberOfCount = winningLottos.addMatchingNumber(WinningLottoType.MATCH_THREE);
        assertThat(matchingNumberOfCount.matchCount).isEqualTo( 3);
    }

    @Test
    void calculateLottoMoney(){
        winningLottos.addMatchingNumber(WinningLottoType.MATCH_THREE);
        winningLottos.addMatchingNumber(WinningLottoType.MATCH_FOUR);
        winningLottos.addMatchingNumber(WinningLottoType.MATCH_FIVE);
        winningLottos.addMatchingNumber(WinningLottoType.MATCH_SIX);
        assertThat(winningLottos.amountOfWinning()).isEqualTo(new BigDecimal(2001555000));
    }
    @Test
    void calculateLottoMoney_LottyType_ZERO(){
        winningLottos.addMatchingNumber(WinningLottoType.MATCH_ZERO);
        assertThat(winningLottos.amountOfWinning()).isEqualTo(BigDecimal.ZERO);
    }
    @Test
    void winningRevenueTest(){
        winningLottos.addMatchingNumber(WinningLottoType.MATCH_THREE);
        BigDecimal revenue = winningLottos.calcurateRevenue("1000");
        BigDecimal validNumber = new BigDecimal(5).setScale(2, RoundingMode.FLOOR);
        assertThat(revenue).isEqualTo(validNumber);
    }

}