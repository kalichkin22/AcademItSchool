package ru.academits.kalichkin.atm;

public class Account {
    private Banknotes[] cash;
    private int balance;
    private static final int MAX_BANKNOTES = 100;

    public Account() {
        Banknotes _50 = new Banknotes(50, 10);
        Banknotes _100 = new Banknotes(100, 10);
        Banknotes _500 = new Banknotes(500, 10);
        Banknotes _1000 = new Banknotes(1000, 10);
        Banknotes _5000 = new Banknotes(5000, 10);
        this.cash = new Banknotes[]{_50, _100, _500, _1000, _5000};
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
        int countBanknote = sum / nominal;
        int remainSum = sum % nominal;
        boolean isPrintBalance = false;

        if (sum % 50 != 0) {
            System.out.println("Сумма должна быть кратна 50!");
            return;
        }

        int newNominal = 0;
        if (sum > 0) {
            for (Banknotes banknotes : cash) {
                if (nominal == banknotes.getNominal() && remainSum == 0) {
                    if (countBanknote <= banknotes.getCount()) {
                        banknotes.setCount(banknotes.getCount() - countBanknote);
                    } else if (countBanknote > banknotes.getCount()) {
                        newNominal = sum - nominal * banknotes.getCount();
                        banknotes.setCount(0);
                    }
                    isPrintBalance = true;
                } else if (remainSum != 0 && nominal == banknotes.getNominal()) {
                    if (countBanknote <= banknotes.getCount()) {
                        banknotes.setCount(banknotes.getCount() - countBanknote);
                        newNominal = remainSum;
                        sum = sum - (banknotes.getNominal() * countBanknote);
                    } else if (countBanknote > banknotes.getCount()) {
                        newNominal = remainSum;
                        banknotes.setCount(0);
                        sum = sum - (banknotes.getNominal() * countBanknote);
                    }
                    isPrintBalance = true;
                }
            }

            for (Banknotes banknotes : cash) {
                if (newNominal == banknotes.getNominal()) {
                    if (remainSum != 0) {
                        int newCountBanknote = newNominal / remainSum;
                        banknotes.setCount(banknotes.getCount() - newCountBanknote);
                        sum = sum - banknotes.getNominal();
                    } else {
                        banknotes.setCount(banknotes.getCount() - 1);
                        sum = sum - banknotes.getNominal();
                    }
                }
            }
        }
        if (isPrintBalance) {
            System.out.println("Баланс: " + getBalance());
        } else {
            System.out.println("Таких банкнот не существует!");
        }
    }
}

