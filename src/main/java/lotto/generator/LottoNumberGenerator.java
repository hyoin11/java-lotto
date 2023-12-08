package lotto.generator;

import lotto.domain.LottoNumber;

import java.util.List;

@FunctionalInterface
public interface LottoNumberGenerator {
    List<LottoNumber> getLottoNumberList();
}
