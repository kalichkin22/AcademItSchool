package ru.academits.kalichkin.model;

import ru.academits.kalichkin.exception.*;

import java.util.*;

public class Account {
    private final ArrayList<Banknotes> cash;
    public static final int MAX_COUNT_BANKNOTES = 100;

    public Account() {
        cash = new ArrayList<>(Arrays.asList(new Banknotes(10, 10), new Banknotes(50, 10),
                new Banknotes(100, 10), new Banknotes(500, 10),
                new Banknotes(1000, 10), new Banknotes(5000, 10)));
    }


    public ArrayList<Banknotes> getBanknotes() {
        return new ArrayList<>(cash);
    }


    private int indexBanknote(int nominal) {
        int index = 0;
        for (int i = 0; i < cash.size(); i++) {
            if (cash.get(i).getNominal() == nominal) {
                index = i;
                break;
            }
        }
        return index;
    }


    private Banknotes findBanknote(int nominal) {
        return cash.get(indexBanknote(nominal));
    }


    private Banknotes findLastBanknote() {
        return cash.get(cash.size() - 1);
    }


    private void validateNominal(int nominal) {
        if (findBanknote(nominal).getNominal() == nominal) {
            return;
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


    public Integer[] getNominalBanknote() {
        Integer[] items = new Integer[cash.size()];
        for (int i = 0; i < cash.size(); i++) {
            items[i] = cash.get(i).getNominal();
        }
        return items;
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


    public void deposit(int nominal, int count) {
        if (count + getCountAllBanknotes() > MAX_COUNT_BANKNOTES) {
            throw new TooMuchCountBanknoteException();
        }

        validateNominal(nominal);

        Banknotes banknote = findBanknote(nominal);
        banknote.setCount(banknote.getCount() + count);
    }


    public ArrayList<Banknotes> withDraw(int sum, int nominal) {
        if (getBalance() == 0) {
            throw new NotHaveBanknotesException();
        }

        validateNominal(nominal);

        Banknotes banknoteEmpty = new Banknotes(nominal, 0);
        if (cash.contains(banknoteEmpty)) {
            throw new NotSuchCountBanknoteException();
        }

        if (sum > getBalance()) {
            throw new TooMuchSumException();
        }

        int firstBanknoteNominal = cash.get(0).getNominal();
        if (sum % firstBanknoteNominal != 0) {
            throw new NotSuchNominalException();
        }

        int newNominal = nominal;
        ArrayList<Banknotes> cashWithDraw = new ArrayList<>();

        if (sum / newNominal > findBanknote(nominal).getNominal()) {
            throw new NotSuchCountBanknoteException();
        }

        while (sum > 0) {
            Banknotes banknote = findBanknote(newNominal);
            int countBanknote = 0;
            countBanknote += sum / newNominal;

            if (banknote.getCount() == 0 && banknote.getNominal() != firstBanknoteNominal) {
                int nominalLess = cash.get(indexBanknote(newNominal) - 1).getNominal();
                newNominal = findBanknote(nominalLess).getNominal();
            }
            if (countBanknote <= banknote.getCount()) {
                banknote.setCount(banknote.getCount() - countBanknote);
                sum = sum - banknote.getNominal() * countBanknote;
                if (sum != 0 && sum < newNominal && banknote.getNominal() != firstBanknoteNominal) {
                    int nominalLess = cash.get(indexBanknote(newNominal) - 1).getNominal();
                    newNominal = findBanknote(nominalLess).getNominal();
                } else if (sum > 0 && sum < newNominal) {
                    throw new NotSuchCountBanknoteException();
                }
                if (countBanknote != 0) {
                    Banknotes banknoteWithDraw = new Banknotes(banknote.getNominal(), countBanknote);
                    cashWithDraw.add(banknoteWithDraw);
                }
            } else {
                sum = sum - banknote.getNominal() * banknote.getCount();
                if (findLastBanknote().getCount() != 0 && sum > findLastBanknote().getNominal()) {
                    newNominal = findLastBanknote().getNominal();
                }
                if (banknote.getCount() != 0) {
                    Banknotes banknoteWithDraw = new Banknotes(banknote.getNominal(), banknote.getCount());
                    cashWithDraw.add(banknoteWithDraw);
                }
                banknote.setCount(0);
            }

        }
        return cashWithDraw;
    }


    public String toString() {
        return Arrays.toString(cash.toArray());
    }
}








