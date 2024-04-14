package bank_system.repository;

import bank_system.domain.Account;
import bank_system.domain.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {

    private static final Map<Account, List<Transaction>> transactions = new HashMap<>();

    public static void init(Account account) {
        transactions.put(account, new ArrayList<>());
    }

    public static void deposit(Account account, Long amount) {
        Transaction transaction = Transaction.deposit(account, amount);

        transactions.get(account).add(transaction);
    }

    public static void withdraw(Account account, Long amount) {
        Transaction transaction = Transaction.withdraw(account, amount);

        transactions.get(account).add(transaction);
    }

    public static void transfer(Account from, Account to, Long amount) {
        Transaction transaction = Transaction.transfer(from, to, amount);

        transactions.get(from).add(transaction);
    }

    public static List<Transaction> findAllBy(Account account) {
        return transactions.get(account);
    }
}
