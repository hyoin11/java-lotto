package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int PRICE = 1_000;
    private static final String STRING_SPLITTER = ", ";

    private final Set<Integer> values;

    public Lotto(Set<Integer> lotto) {
        LottoNumbers.check(lotto);
        this.values = lotto;
    }

    public static Lotto from(List<Integer> numbers) {
        Set<Integer> newLotto = new HashSet<>();
        for (Integer number : numbers) {
            newLotto.add(number);
        }
        return new Lotto(newLotto);
    }

    public static Lotto from(String stringNumbers) {
        Set<Integer> newLotto = new HashSet<>();
        String[] numbers = stringNumbers.split(STRING_SPLITTER);
        for (String number : numbers) {
            newLotto.add(Integer.parseInt(number));
        }
        return new Lotto(newLotto);
    }

    public int matchCount(Lotto otherLotto) {
        return (int) values.stream().filter(number -> otherLotto.contains(number)).count();
    }

    public boolean contains(int number) {
        return values.contains(number);
    }

    @Override
    public String toString() {
        List<Integer> lottoList = values.stream().collect(Collectors.toList());
        Collections.sort(lottoList);
        return lottoList.toString();
    }
}
