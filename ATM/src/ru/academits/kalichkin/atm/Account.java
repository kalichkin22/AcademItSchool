package ru.academits.kalichkin.atm;

import ru.academits.kalichkin.exception.*;

import java.util.*;

public class Account {
    private LinkedList<Banknotes> cash;
    private Banknotes[] banknotes;
    public static final int MAX_COUNT_BANKNOTES = 100;

    public Account() {
        banknotes = new Banknotes[]{new Banknotes(50, 10),
                new Banknotes(100, 10), new Banknotes(500, 10),
                new Banknotes(1000, 10), new Banknotes(5000, 10)};
        cash = new LinkedList<>(Arrays.asList(banknotes));
    }

    public LinkedList<Banknotes> getBanknotes() {
        return cash;
    }

    private void validateNominal(int nominal) {
        for (Banknotes banknote : banknotes) {
            if (banknote.getNominal() == nominal) {
                return;
            }
        }
        throw new NoSuchElementException();
    }


    public int getMinNominal() {
        if (cash.peekFirst() == null) {
            throw new NotHaveBanknotesException();
        }
        return cash.peekFirst().getNominal();
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

        validateNominal(nominal);

        Banknotes newBanknote = new Banknotes(nominal, 0);
        if (!cash.contains(newBanknote)) {
            if (cash.isEmpty()) {
                cash.addFirst(newBanknote);
            } else if (nominal < cash.peekFirst().getNominal()) {
                cash.addFirst(newBanknote);
            } else {
                cash.add(newBanknote);
            }
        }

        for (Banknotes banknote : cash) {
            if (nominal == banknote.getNominal()) {
                banknote.setCount(banknote.getCount() + count);
                return true;
            }
        }
        return false;
    }


    public LinkedList<Banknotes> withDraw(int sum, int nominal) {
        validateNominal(nominal);

        if (sum > getBalance()) {
            throw new TooMuchSumException();
        }

        int getFirstBanknoteNominal = cash.peekFirst().getNominal();
        if (sum % getFirstBanknoteNominal != 0) {
            throw new NotSuchNominalException();
        }

        int multiplyFirstBanknote = 100;
        if ((sum - cash.getFirst().getCount() * getFirstBanknoteNominal) % multiplyFirstBanknote == getFirstBanknoteNominal
                && nominal == getFirstBanknoteNominal) {
            throw new NotSuchCountBanknoteException();
        }

        int newNominal = nominal;
        int countBanknote;
        LinkedList<Banknotes> newCash = new LinkedList<>();

        while (sum != 0) {
            for (int i = 0; i < cash.size(); i++) {
                if (newNominal == cash.get(i).getNominal()) {
                    countBanknote = 0;
                    countBanknote += sum / newNominal;

                    if (countBanknote <= cash.get(i).getCount()) {
                        cash.get(i).setCount(cash.get(i).getCount() - countBanknote);
                        sum = sum - cash.get(i).getNominal() * countBanknote;
                        if (sum != 0 && sum < newNominal && cash.get(i).getNominal() != getFirstBanknoteNominal) {
                            newNominal = cash.get(i - 1).getNominal();
                        } else if (sum > 0 && sum < newNominal) {
                            throw new NotSuchCountBanknoteException();
                        }

                        Banknotes newBanknote = new Banknotes(cash.get(i).getNominal(), countBanknote);

                        if (cash.get(i).getCount() == 0) {
                            cash.remove(cash.get(i));
                        }

                        if (newBanknote.getCount() != 0) {
                            newCash.add(newBanknote);
                        }
                    } else {
                        int count = cash.get(i).getCount();
                        sum = sum - cash.get(i).getNominal() * count;
                        Banknotes newBanknote = new Banknotes(cash.get(i).getNominal(),cash.get(i).getCount());
                        cash.remove(cash.get(i));
                        newNominal = cash.peekLast().getNominal();
                        newCash.add(newBanknote);
                    }
                }
            }
        }
        return newCash;
    }


    public String toString() {
        return Arrays.toString(cash.toArray());
    }

    public int getNominalBanknote(Banknotes banknote) {
        return banknote.getNominal();
    }

    public int getCountBanknote(Banknotes banknote) {
        return banknote.getCount();
    }
}








