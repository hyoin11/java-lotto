package lotto.domain;

import java.util.List;

public class LottoResult implements Rate{

    private static final double PERCENT_FOR_RATE = 100.0;

    private List<LottoPrize> result;

    private double rate;

    public LottoResult(List<LottoPrize> result) {
        this.result = result;
        this.rate = 0;
    }

    public double getRate() {
        return rate;
    }

    public int countingPrize(LottoPrize findPrize) {
        return (int) result.stream()
                .filter(prize -> prize.equals(findPrize))
                .count();
    }

    long totalPrize() {
        return result.stream()
                .mapToLong(prize -> prize.getPrize())
                .sum();
    }

    @Override
    public void calculateRate(int money) {
        double totalPrize = totalPrize();
        this.rate = Math.round(totalPrize / money * PERCENT_FOR_RATE) / PERCENT_FOR_RATE;
    }
}
