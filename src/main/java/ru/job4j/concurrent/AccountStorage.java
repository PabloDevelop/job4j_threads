package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("accounts")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public boolean add(Account account) {
        synchronized (accounts) {
            return accounts.put(account.id(), account) != null;
        }
    }

    public boolean update(Account account) {
        synchronized (accounts) {
            Account acc = accounts.get(account.id());
            boolean rsl = false;
            if (acc != null) {
                rsl = accounts.replace(acc.id(), account) != null;
            }
            return rsl;
        }
    }

    public boolean delete(int id) {
        synchronized (accounts) {
            Account acc = accounts.get(id);
            boolean rsl = false;
            if (acc != null) {
                rsl = accounts.remove(acc.id()) != null;
            }
            return rsl;
        }
    }

    public Optional<Account> getById(int id) {
        synchronized (accounts) {
            return Optional.ofNullable(accounts.get(id));
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (accounts) {
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
}