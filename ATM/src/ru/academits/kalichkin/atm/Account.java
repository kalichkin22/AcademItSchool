package ru.academits.kalichkin.atm;

import java.util.*;

public class Account {
    private LinkedList<Banknotes> cash;
    private int balance;
    private static final int MAX_BANKNOTES = 100;

    public Account() {
        cash = new LinkedList<>(Arrays.asList(new Banknotes(50, 10),
                new Banknotes(100, 10), new Banknotes(500, 10),
                new Banknotes(1000, 10), new Banknotes(5000, 10)));
    }

    public int getBalance() {
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
        int newNominal = nominal;
        int countBanknote;

        while (sum != 0) {
            for (int j = 0; j < cash.size(); j++) {
                if (newNominal == cash.get(j).getNominal()) {
                    countBanknote = 0;
                    countBanknote += sum / newNominal;
                    if (countBanknote <= cash.get(j).getCount()) {
                        cash.get(j).setCount(cash.get(j).getCount() - countBanknote);
                        sum = sum - cash.get(j).getNominal() * countBanknote;
                        if (sum != 0 && sum < newNominal && cash.get(j).getNominal() != cash.peekFirst().getNominal()) {
                            newNominal = cash.get(j - 1).getNominal();
                        } else if (sum > 0 && sum < newNominal){
                            System.out.println("К сожалению, недостаточно банкнот имеющегося номинала для выдачи суммы");
                            return;
                        }
                    } else {
                        int count = cash.get(j).getCount();
                        sum = sum - cash.get(j).getNominal() * count;
                        cash.get(j).setCount(0);
                        cash.remove(cash.get(j));

                        if (sum < cash.get(j).getNominal()) {
                            newNominal = cash.peekFirst().getNominal();
                        } else {
                            newNominal = cash.peekLast().getNominal();
                        }
                    }
                }
            }
        }
        System.out.println("Баланс: " + getBalance());
    }
}








