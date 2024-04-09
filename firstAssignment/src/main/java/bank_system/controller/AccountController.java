package bank_system.controller;

import bank_system.domain.Account;
import bank_system.repository.TransactionRepository;

import java.util.List;

public class AccountController {

    public static final String BALANCE_INFO = "원 있습니다.";
    public static final String NO_TRANSACTION_HISTORY = "거래 기록이 없습니다.";

    public static String checkBalanceOf(Account account) {
        Long balance = account.getBalance();

        return balance.toString() + BALANCE_INFO;
    }
}
