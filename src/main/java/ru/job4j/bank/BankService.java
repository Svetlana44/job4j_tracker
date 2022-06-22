package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает модель сервисов банка
 *
 * @author Svetlana T
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей осуществляется в
     * коллекции типа Map
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя и добавляет, при отсутствии, его в словарь Map.
     * Если встречаются 2 задания с одинаковым приоритетом, то в очереди
     * они распределяются по принципу FIFO.
     *
     * @param user пользователь, который добавляется в словарь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход пользователя и его аккаунт, добавляет аккаунт, при отсутствии, в список аккаунтов пользователя.
     * Если встречаются 2 задания с одинаковым приоритетом, то в очереди
     * они распределяются по принципу FIFO.
     *
     * @param passport паспорт, по которому ищется пользователь
     * @param account  аккаун, который добавляется в список аккаунтов пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод позволяет получить пользователя по номеру паспорта
     *
     * @param passport паспорт, по которому ищется пользователь
     * @return возвращает пользователя или null если такого номера паспорта нет
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (Objects.equals(passport, user.getPassport())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод позволяет получить аккаунт пользователя по паспотрту пользователя и реквизитам аккаунта
     *
     * @param passport паспорт, по которому ищется пользователь
     * @return возвращает аккаунт или null если такого пользователя или реквизитов нет
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Метод позволяет перевести деньги с одного аккаунта на другой аккаунт
     *
     * @param srcPassport   паспорт счёта отправителя
     * @param destPassport  паспорт счёта получателя
     * @param srcRequisite  реквизиты счёта отправителя
     * @param destRequisite реквизиты счёта получателя
     * @param amount        сумма перевода
     * @return возвращает true, если перевод произошёл и false, если не произошёл
     * (не существует один из счетов или на счёте отправителя не достаточно средств)
     */

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        Account accountSrc = findByRequisite(srcPassport, srcRequisite);
        Account accountDest = findByRequisite(destPassport, destRequisite);
        if (accountSrc != null && accountSrc.getBalance() >= amount && accountDest != null) {
            accountDest.setBalance(accountDest.getBalance() + amount);
            accountSrc.setBalance(accountSrc.getBalance() - amount);
            return true;
        }
        return false;
    }
}
