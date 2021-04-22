package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Okno extends JFrame {
    public Okno(){
        super();
        this.setTitle("Veza v bludisku by Peter Takacs 104604");
        this.setVisible(true);
        this.setSize(500,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HraciaPlocha plocha = new HraciaPlocha();
        plocha.vytvorBludisko();
        plocha.inicializujSusedov();
        ArrayList<Policko> bludisko = plocha.getBludisko();
        plocha.vytvorCesty(bludisko.get(21));

        MojePlatno platno = new MojePlatno(bludisko);
        platno.setSize(new Dimension(400, 400));

        this.add(platno);
        this.pack();
    }
}
