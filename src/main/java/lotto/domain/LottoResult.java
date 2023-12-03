package lotto.domain;

import java.util.List;

public class LottoResult implements Rate{

    private static final double PERCENT_FOR_RATE = 100.0;

    private List<LottoPrize> result;

    public LottoResult(List<LottoPrize> result) {
        this.result = result;
    }

    public int countingPrize(LottoPrize findPrize) {
        return (int) result.stream()
                .filter(prize -> prize.equals(findPrize))
                .count();
    }

    int totalPrize() {
        return result.stream()
                .mapToInt(prize -> prize.getPrize())
                .sum();
    }

    public double getRate(LottoMoney money) {
        return getRate(money.getMoney());
    }

    @Override
    public double getRate(int money) {
        double totalPrize = totalPrize();
        return Math.round(totalPrize / money * PERCENT_FOR_RATE) / PERCENT_FOR_RATE;
    }
}
