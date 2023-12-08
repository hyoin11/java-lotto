package lotto.service;

import lotto.domain.LottoBuyInfo;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.ManualLottos;
import lotto.domain.WinningLotto;

public class LottoService {

    public static Lottos purchaseLotto(LottoBuyInfo buyInfo, ManualLottos manualLottoList) {
        Lottos manualLottos = Lottos.from(manualLottoList);
        Lottos autoLottos = Lottos.from(buyInfo.getAutoCount());

        return Lottos.of(manualLottos, autoLottos);
    }

    public static LottoResult executeStatistics(Lottos lottos, WinningLotto winningLotto, LottoBuyInfo buyInfo) {
        LottoResult result = lottos.match(winningLotto);
        result.calculateRate(buyInfo.getMoney());
        return result;
    }
}
