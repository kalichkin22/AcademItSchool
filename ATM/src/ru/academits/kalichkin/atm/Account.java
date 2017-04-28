package ru.academits.kalichkin.atm;

import java.util.*;

public class Account {
    private LinkedList<Banknotes> cash;
    private static final int MAX_BANKNOTES = 100;

    public Account() {
        cash = new LinkedList<>(Arrays.asList(new Banknotes(50, 10),
                new Banknotes(100, 10), new Banknotes(500, 10),
                new Banknotes(1000, 10), new Banknotes(5000, 10)));
    }

    public int getBalance() {
        int balance = 0;
        for (Banknotes aCash : cash) {
            balance += aCash.getNominal() * aCash.getCount();
        }
        return balance;
    }

    private int getCountBanknotes() {
        int count = 0;
        for (Banknotes aCash : cash) {
            count += aCash.getCount();
        }
        return count;
    }

    public void deposit(int nominal, int count) {
        int maxCount = MAX_BANKNOTES - getCountBanknotes();
        for (Banknotes banknote : cash) {
            if (nominal == banknote.getNominal()) {
                if (count + getCountBanknotes() <= MAX_BANKNOTES) {
                    banknote.setCount(banknote.getCount() + count);
                } else {
                    banknote.setCount(maxCount);
                    System.out.println("Банкомат не может принять такое количество банкнот, сейчас можно внести максимум " + maxCount);
                }
                System.out.println("Баланс: " + getBalance());
                return;
            }
        }
        System.out.println("Таких банкнот не существует! За Вами уже выехали!!!");
    }


    public void withDraw(int sum, int nominal) {
        if (sum > getBalance()) {
            System.out.println("В банкомате нет такой суммы");
            return;
        }

        if (sum % cash.peekFirst().getNominal() != 0) {
            System.out.println("Сумма должна быть кратна " + cash.peekFirst().getNominal());
            return;
        }

        if (nominal % cash.peekFirst().getNominal() != 0) {
            System.out.println("Таких банкнот не существует!");
            return;
        }

        int newNominal = nominal;
        int countBanknote;

        while (sum != 0) {
            for (int i = 0; i < cash.size(); i++) {
                if (newNominal == cash.get(i).getNominal()) {
                    countBanknote = 0;
                    countBanknote += sum / newNominal;

                    if (countBanknote <= cash.get(i).getCount()) {
                        cash.get(i).setCount(cash.get(i).getCount() - countBanknote);
                        sum = sum - cash.get(i).getNominal() * countBanknote;
                        if (sum != 0 && sum < newNominal && cash.get(i).getNominal() != cash.peekFirst().getNominal()) {
                            newNominal = cash.get(i - 1).getNominal();
                        } else if (sum > 0 && sum < newNominal) {
                            System.out.println("К сожалению, недостаточно банкнот имеющегося номинала для выдачи суммы");
                            return;
                        }
                    } else {
                        int count = cash.get(i).getCount();
                        sum = sum - cash.get(i).getNominal() * count;
                        cash.get(i).setCount(0);
                        cash.remove(cash.get(i));
                        newNominal = cash.peekLast().getNominal();
                    }
                }
            }
        }
        System.out.println("Баланс: " + getBalance());
    }
}








