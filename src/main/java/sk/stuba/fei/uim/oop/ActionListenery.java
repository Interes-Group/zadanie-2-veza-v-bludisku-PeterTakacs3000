package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class ActionListenery implements ActionListener {
    protected ArrayList<Policko> bludisko;
    protected int poziciaHraca;
    protected int poziciaCiela;
    protected JLabel hrac;

    public ActionListenery(ArrayList<Policko> bludisko, int poziciaHraca, int poziciaCiela, JLabel hrac) {
        this.bludisko = bludisko;
        this.poziciaHraca = poziciaHraca;
        this.poziciaCiela = poziciaCiela;
        this.hrac = hrac;
    }
}
