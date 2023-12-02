package lotto.domain;

public class LottoMoney {

    private final int money;

    public LottoMoney(int money) {
        if (money < Lotto.PRICE) {
            throw new IllegalArgumentException("유효하지 않은 값입니다.");
        }
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public int lottoCount() {
        return money / Lotto.PRICE;
    }
}
