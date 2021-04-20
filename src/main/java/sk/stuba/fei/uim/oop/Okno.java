package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Okno extends JFrame {
    public Okno(){
        super();
        this.setTitle("Veza v bludisku by Peter Takacs 104604");
        this.setVisible(true);
        this.setSize(430,330);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HraciaPlocha plocha = new HraciaPlocha();
        plocha.vytvorBludisko();
        ArrayList bludisko = plocha.getBludisko();

        MojePlatno platno = new MojePlatno(bludisko);
        platno.setSize(new Dimension(330, 330));

        this.add(platno);
        this.pack();
    }
}
