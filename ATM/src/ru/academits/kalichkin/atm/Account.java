package ru.academits.kalichkin.atm;

import ru.academits.kalichkin.exception.*;

import java.util.*;

public class Account {
    private ArrayList<Banknotes> cash;
    public static final int MAX_COUNT_BANKNOTES = 100;

    public Account() {
        cash = new ArrayList<>(Arrays.asList(new Banknotes(10, 10), new Banknotes(50, 10),
                new Banknotes(100, 10), new Banknotes(500, 10),
                new Banknotes(1000, 10), new Banknotes(5000, 10)));
    }


    public ArrayList<Banknotes> getBanknotes() {
        return cash;
    }


    private void validateNominal(ArrayList<Banknotes> cash, int nominal) {
        for (Banknotes banknote : cash) {
            if (banknote.getNominal() == nominal) {
                return;
            }
        }
        throw new NoSuchElementException();
    }


    public int getMinNominal() {
        for (Banknotes banknote : cash) {
            if (banknote.getCount() != 0) {
                return banknote.getNominal();
            }
        }
        throw new NotHaveBanknotesException();
    }


    public int getBalance() {
        int balance = 0;
        for (Banknotes banknote : cash) {
            balance += banknote.getNominal() * banknote.getCount();
        }
        return balance;
    }


    public int getCountAllBanknotes() {
        int count = 0;
        for (Banknotes aCash : cash) {
            count += aCash.getCount();
        }
        return count;
    }


    public boolean deposit(int nominal, int count) {
        if (count + getCountAllBanknotes() > MAX_COUNT_BANKNOTES) {
            throw new TooMuchCountBanknoteException();
        }

        validateNominal(cash, nominal);

        for (Banknotes banknote : cash) {
            if (nominal == banknote.getNominal()) {
                banknote.setCount(banknote.getCount() + count);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Banknotes> withDraw(int sum, int nominal) {
        validateNominal(cash, nominal);

        if (sum > getBalance()) {
            throw new TooMuchSumException();
        }

        int firstBanknoteNominal = cash.get(0).getNominal();
        if (sum % firstBanknoteNominal != 0) {
            throw new NotSuchNominalException();
        }

        int newNominal = nominal;
        ArrayList<Banknotes> cashWithDraw = new ArrayList<>();

        while (sum != 0) {
            for (int i = 0; i < cash.size(); i++) {
                if (newNominal == cash.get(i).getNominal()) {
                    int countBanknote = 0;
                    countBanknote += sum / newNominal;
                    Banknotes banknoteWithDraw;

                    if (countBanknote <= cash.get(i).getCount()) {
                        cash.get(i).setCount(cash.get(i).getCount() - countBanknote);
                        sum = sum - cash.get(i).getNominal() * countBanknote;
                        if (sum != 0 && sum < newNominal && cash.get(i).getNominal() != firstBanknoteNominal) {
                            newNominal = cash.get(i - 1).getNominal();
                            if (cash.get(i).getCount() == 0 || countBanknote == 0) {
                                break;
                            }
                        } else if (sum > 0 && sum < newNominal) {
                            throw new NotSuchCountBanknoteException();
                        }

                        banknoteWithDraw = new Banknotes(cash.get(i).getNominal(), countBanknote);
                        cashWithDraw.add(banknoteWithDraw);

                    } else {
                        if (cash.get(i).getCount() == 0 && cash.get(i).getNominal() != firstBanknoteNominal) {
                            newNominal = cash.get(i - 1).getNominal();
                            break;
                        }

                        sum = sum - cash.get(i).getNominal() * cash.get(i).getCount();
                        banknoteWithDraw = new Banknotes(cash.get(i).getNominal(), cash.get(i).getCount());
                        newNominal = cash.get(cash.size() - 1).getNominal();
                        cash.get(i).setCount(0);
                        cashWithDraw.add(banknoteWithDraw);
                    }
                }
            }
        }
        return cashWithDraw;
    }

    public String toString() {
        return Arrays.toString(cash.toArray());
    }
}








