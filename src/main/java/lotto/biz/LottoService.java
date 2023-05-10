package lotto.biz;

import lotto.model.Lotto;
import lotto.model.LottoGames;
import lotto.model.enums.MatchingStrategy;
import lotto.util.ParseUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class LottoService {
    public static final String COMMA = ",";
    private static String SHOW_GAME_COUNT_FORMAT = "%s개를 구매했습니다.";

    private LottoGames games;

    public LottoService() {
        games = new LottoGames();
    }

    public void setPurchaseValue(int purchaseValue) {
        int lottoCount = purchaseValue / Lotto.LOTTO_PRICE;
        System.out.println(String.format(
                SHOW_GAME_COUNT_FORMAT, lottoCount)
        );
        games= new LottoGames(lottoCount);
    }

    public void setWinningNumbers(String winningNumbers) {
        if(games == null){
            throw new RuntimeException("구입 금액을 먼저 입력해주세요.");
        }

        games.setWinningNumbers(ParseUtil.convertStringToIntegerList(winningNumbers, COMMA));
    }

    public void aggregateWinningStatistics() {
        games.aggregate();
    }


    public Map<MatchingStrategy, List<Lotto>> getStatistic(){
        return games.getStatistic();
    }

    public int getPurchaseValue() {
        return games.getGameCount() * Lotto.LOTTO_PRICE;
    }

    public Set<Integer> getWinningNumbers(){
        return games.getWinningNumbers();
    }

    public List<Lotto> getGames(){
        return games.getGames();
    }

    public void setBonusNumber(int input) {
        games.setBonusNumber(input);
    }

    public Integer getBonusNumber() {
        return games.getBonusNumber();
    }
}