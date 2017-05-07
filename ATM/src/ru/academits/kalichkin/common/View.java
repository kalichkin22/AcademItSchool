package ru.academits.kalichkin.common;

import ru.academits.kalichkin.model.Banknotes;
import java.util.ArrayList;

public interface View {

    void startApplication();

    void onBalance(int balance);

    void onGetBanknote(ArrayList<Banknotes> list);

    void onWithDraw(ArrayList<Banknotes> list);

    void addViewListener(ViewListener listener);

    void removeViewListener(ViewListener listener);
}

