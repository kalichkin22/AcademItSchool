package ru.academits.kalichkin;

import ru.academits.kalichkin.common.View;
import ru.academits.kalichkin.controller.Controller;
import ru.academits.kalichkin.model.Account;
import ru.academits.kalichkin.view.AppView;

public class AtmApp {
    public static void main(String[] args) {
        View view = new AppView();
        Account account = new Account();
        Controller controller = new Controller(account, view);
        view.addViewListener(controller);
        view.startApplication();
    }
}
