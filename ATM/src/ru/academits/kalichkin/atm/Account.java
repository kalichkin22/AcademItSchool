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
        int newNominal = nominal;
        int countBanknote;

        while (sum > 0) {
            for (int j = 0; j < cash.length; j++) {
                if (newNominal == cash[j].getNominal()) {
                    countBanknote = 0;
                    countBanknote += sum / newNominal;
                    if (countBanknote <= cash[j].getCount()) {
                        cash[j].setCount(cash[j].getCount() - countBanknote);
                        sum = sum - cash[j].getNominal() * countBanknote;
                        if (cash[j].getNominal() == cash[0].getNominal()) {
                            newNominal = cash[0].getNominal();
                        } else {
                            newNominal = cash[j - 1].getNominal();
                        }
                    } else {
                        int count = cash[j].getCount();
                        sum = sum - cash[j].getNominal() * count;
                        cash[j].setCount(0);
                        if (cash[j].getNominal() == cash[0].getNominal()) {
                            newNominal = cash[0].getNominal();
                        } else {
                            newNominal = cash[j - 1].getNominal();
                        }

                    }
                    if (sum <= 0) {
                        System.out.println(getBalance());
                        return;
                    }
                }
            }
        }
        System.out.println(getBalance());
    }
}

