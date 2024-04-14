package bank_system.controller;

import bank_system.domain.Account;
import bank_system.domain.Transaction;
import bank_system.io.InputView;
import bank_system.io.OutputView;
import bank_system.repository.AccountRepository;
import bank_system.repository.TransactionRepository;
import bank_system.utils.exception.NoSuchAccountException;
import bank_system.utils.exception.ShortMoneyException;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionController {

    private static final String DEPOSIT_COMPLETED = "입금이 완료되었습니다.";
    private static final String WITHDRAW_COMPLETED = "출금이 완료되었습니다.";
    private static final String TRANSFER_COMPLETED = "송금이 완료되었습니다.";

    public static String deposit(Account account) {
        Long amount = InputView.readMoney();

        TransactionRepository.deposit(account, amount);

        return DEPOSIT_COMPLETED;
    }

    public static String withdraw(Account account) {
        Long amount = InputView.readMoney();

        try {
            TransactionRepository.withdraw(account, amount);
            return WITHDRAW_COMPLETED;
        } catch (ShortMoneyException e) {
            OutputView.writeErrorMessageOf(e);

            return withdraw(account);
        }
    }

    public static String transfer(Account account) {
        Account toAccount = findAccount();
        Long amount = InputView.readMoney();

        try {
            TransactionRepository.transfer(account, toAccount, amount);
            return TRANSFER_COMPLETED;
        } catch (ShortMoneyException e) {
            OutputView.writeErrorMessageOf(e);

            return withdraw(account);
        }
    }

    private static Account findAccount() {
        try {
            String accountId = InputView.readAccountNumber();
            return AccountRepository.findBy(accountId).orElseThrow();
        } catch (NoSuchAccountException e) {
            OutputView.writeErrorMessageOf(e);

            return findAccount();
        }
    }

    public static String getTransactionHistoryOf(Account account) {
        List<Transaction> transactions = TransactionRepository.findAllBy(account);

        return transactions.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
