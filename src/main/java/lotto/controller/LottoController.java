package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoBuyInfo;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.view.LottoInputView;
import lotto.view.LottoResultView;

public class LottoController {

    public static void run() {
        LottoBuyInfo buyInfo = LottoInputView.inputBuyInfo();
        Lottos lottos = LottoService.purchaseLotto(buyInfo);
        LottoResultView.showLottos(buyInfo, lottos);

        WinningLotto winningLotto = new WinningLotto(Lotto.from(LottoInputView.inputWinningNumbers()), LottoNumber.from(LottoInputView.inputBonusNumber()));
        LottoResult result = LottoService.executeStatistics(lottos, winningLotto, buyInfo);
        LottoResultView.showResult(result);
    }
}
