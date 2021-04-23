package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MojePlatno extends JPanel {

    private ArrayList<Policko> hraciaPlocha;

    public MojePlatno(ArrayList<Policko> hraciaPlocha){
        this.hraciaPlocha = hraciaPlocha;
    }

    public void kresli(){
        int index = 0;
        this.setVisible(true);
        this.setSize(400,400);
        this.setLayout(new GridLayout(20,20));
        for(int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                Policko aktualnePolicko = hraciaPlocha.get(index);
                var policko = new JLabel("*");
                //policko.setBounds(x * 20,y * 20,20,20);


                if (aktualnePolicko.isWall()) {
                    policko.setBackground(Color.BLACK);
                }
                else if(aktualnePolicko.isFinish()){
                    policko.setBackground(Color.RED);
                }
                else {
                    policko.setBackground(Color.WHITE);
                }

                policko.setOpaque(true);
                policko.setVisible(true);
                this.add(policko);
                index++;

            }
        }
    }


/*
    @Override
    public void paint(Graphics g) {
        int index = 0;
        this.setVisible(true);
        this.setSize(400,400);
        for(int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                Policko aktualnePolicko = hraciaPlocha.get(index);
                if (aktualnePolicko.isWall()) {
                    g.setColor(Color.BLACK);
                }
                else if(aktualnePolicko.isFinish()){
                    g.setColor(Color.RED);
                }
                else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * 20, y * 20, 20, 20);
                index++;

            }
        }
    }
    */
}
