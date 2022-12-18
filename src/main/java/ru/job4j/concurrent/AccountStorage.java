package ru.job4j.concurrent;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {

    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.put(account.id(), account) != null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        Optional<Account> accFrom = getById(fromId);
        Optional<Account> accTo = getById(toId);
        if (accFrom.isPresent() && accTo.isPresent() && accFrom.get().amount() >= amount) {
            int newAmountFrom = accFrom.get().amount() - amount;
            int newAmountTo = accTo.get().amount() + amount;
            rsl = update(new Account(accFrom.get().id(), newAmountFrom))
                    && update(new Account(accTo.get().id(), newAmountTo));
        }
        return rsl;
    }
}