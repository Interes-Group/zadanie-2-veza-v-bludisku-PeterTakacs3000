package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeftButton implements ActionListener {

    private ArrayList<Policko> bludisko;
    private int poziciaHraca;
    private int poziciaCiela;
    private JLabel hrac;

    public LeftButton(ArrayList<Policko> bludisko, int poziciaHraca, int poziciaCiela, JLabel hrac) {
        this.bludisko = bludisko;
        this.poziciaHraca = poziciaHraca;
        this.poziciaCiela = poziciaCiela;
        this.hrac = hrac;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!bludisko.get(poziciaHraca - 1).isWall()){
            this.hrac.setLocation(hrac.getX() - 20, hrac.getY());
            bludisko.get(poziciaHraca - 1).setPlayer(true);
            bludisko.get(poziciaHraca).setPlayer(false);
            poziciaHraca -= 1;
        }
    }
}
