package ru.academits.kalichkin.main;

import ru.academits.kalichkin.atm.Account;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Account account = new Account();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Добрый день!" + System.lineSeparator() +
                    "Вас приветствует Банк Банков!" + System.lineSeparator() +
                    "1. Узнать баланс счета" + System.lineSeparator() +
                    "2. Пополнить баланс" + System.lineSeparator() +
                    "3. Снять деньги" + System.lineSeparator() +
                    "4. Выйти"
            );

            while (true) {
                System.out.println("Выбирете номер операции: ");
                int number = scanner.nextInt();

                switch (number) {
                    case 1:
                        System.out.println("Баланс составляет: " + account.getBalance());
                        break;
                    case 2:
                        Scanner scanner2 = new Scanner(System.in);

                        System.out.println("Введите номинал банкноты: ");
                        int nominalDeposit = scanner2.nextInt();

                        System.out.println("Введите количество банкноты: ");
                        int countDeposit = scanner2.nextInt();

                        account.deposit(nominalDeposit, countDeposit);
                        break;
                    case 3:
                        Scanner scanner3 = new Scanner(System.in);

                        System.out.println("Введите сумму выдачи, кратную 50: ");
                        int sum = scanner3.nextInt();

                        System.out.println("Какими банкнотами произвести выдачу? ");
                        int banknote = scanner3.nextInt();

                        account.withDraw(sum, banknote);
                        break;
                    case 4:
                        System.out.println("До свидания!");
                        break;
                    default:
                        System.out.print("Неизвестная операция, попробуйте еще раз." + System.lineSeparator());
                }

                if (number == 4) {
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Неверная операция, можно вводить только цифры.");
        }
    }

}
