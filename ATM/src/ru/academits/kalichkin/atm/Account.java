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
                if (nominal == banknotes.getNominal()) {
                    if (remainSum == 0) {
                        if (countBanknote <= banknotes.getCount()) {
                            banknotes.setCount(banknotes.getCount() - countBanknote);
                        } else if (countBanknote > banknotes.getCount()) {
                            newNominal = sum - nominal * banknotes.getCount();
                            banknotes.setCount(0);
                        }
                        isPrintBalance = true;
                    } else {
                        if (countBanknote <= banknotes.getCount()) {
                            banknotes.setCount(banknotes.getCount() - countBanknote);
                            newNominal = remainSum;
                            sum = sum - (banknotes.getNominal() * countBanknote);
                        } else if (countBanknote > banknotes.getCount()) {
                            newNominal = sum - (banknotes.getNominal() * banknotes.getCount() + remainSum);
                            banknotes.setCount(0);
                            sum = sum - (banknotes.getNominal() * banknotes.getCount());
                        }
                        isPrintBalance = true;
                    }
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


    public void withDraw2(int sum, int nominal) {
        int q = nominal;
        int x = 0;

        while (sum > 0) {
            for (int j = 0; j < cash.length; j++) {
                x = q;
                if (x == cash[j].getNominal()) {
                    x = 0;
                    x += sum / q;
                    if (x <= cash[j].getCount()) {
                        cash[j].setCount(cash[j].getCount() - x);
                        sum = sum - cash[j].getNominal() * x;
                        if (cash[j].getNominal() == cash[0].getNominal()) {
                            q = cash[0].getNominal();
                        } else {
                            q = cash[j - 1].getNominal();
                        }
                    } else {
                        int count = cash[j].getCount();
                        sum = sum - cash[j].getNominal() * count;
                        cash[j].setCount(0);
                        if (cash[j].getNominal() == cash[0].getNominal()) {
                            q = cash[0].getNominal();
                        } else {
                            q = cash[j - 1].getNominal();
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

