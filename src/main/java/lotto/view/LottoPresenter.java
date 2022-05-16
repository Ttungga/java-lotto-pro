package lotto.view;

import static lotto.domain.message.RequestMessage.PAYMENT;
import static lotto.domain.message.RequestMessage.WINNING_NUMBERS;

import java.util.Scanner;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoNumbersFactory;
import lotto.domain.LottoPayment;
import lotto.domain.LottoPaymentFactory;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.LottoTicketsFactory;

public class LottoPresenter {
    private final LottoPaymentFactory lottoPaymentFactory;
    private final LottoNumbersFactory lottoNumbersFactory;
    private final LottoTicketsFactory lottoTicketsFactory;

    public LottoPresenter(final LottoPaymentFactory lottoPaymentFactory,
                          final LottoNumbersFactory lottoNumbersFactory,
                          final LottoTicketsFactory lottoTicketsFactory) {
        this.lottoPaymentFactory = lottoPaymentFactory;
        this.lottoNumbersFactory = lottoNumbersFactory;
        this.lottoTicketsFactory = lottoTicketsFactory;
    }

    public void present() {
        final Scanner scanner = new Scanner(System.in);
        final LottoPayment payment = requestPayment(scanner);
        final LottoTickets tickets = lottoTicketsFactory.createAutomatically(payment.getPurchasableAmount());
        tickets.print();
        printLineSeparator();
        final LottoNumbers winningNumbers = requestWinningNumbers(scanner);
        new LottoResult(payment, tickets, winningNumbers).printResult();
    }

    private LottoPayment requestPayment(final Scanner scanner) {
        System.out.println(PAYMENT.getMessage());
        try {
            final LottoPayment payment = lottoPaymentFactory.create(scanner.nextLine());
            printPaymentResult(payment);
            return payment;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestPayment(scanner);
        }
    }

    private void printPaymentResult(final LottoPayment payment) {
        System.out.println(String.format("%d개를 구매했습니다.", payment.getPurchasableAmount()));
    }

    private LottoNumbers requestWinningNumbers(final Scanner scanner) {
        System.out.println(WINNING_NUMBERS.getMessage());
        try {
            return lottoNumbersFactory.convertAndCreate(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestWinningNumbers(scanner);
        }
    }

    private void printLineSeparator() {
        System.out.println();
    }
}
