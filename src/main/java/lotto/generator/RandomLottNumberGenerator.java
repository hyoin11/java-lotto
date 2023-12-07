package lotto.generator;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottNumberGenerator implements LottoNumberGenerator{

    private static final int MIN_SIZE = 0;
    private static final int MAX_SIZE = 6;

    private static List<LottoNumber> NUMBERS = new ArrayList<>();

    static {
        for (int i = 1; i <= 45; i++) {
            NUMBERS.add(LottoNumber.from(i));
        }
    }

    @Override
    public List<LottoNumber> getLottoNumberList() {
        Collections.shuffle(NUMBERS);
        List<LottoNumber> newNumbers = NUMBERS.subList(MIN_SIZE, MAX_SIZE);
        Collections.sort(newNumbers);
        return newNumbers;
    }
}
