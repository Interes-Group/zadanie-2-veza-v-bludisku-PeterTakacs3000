package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.util.ArrayList;

public class MojePlatno extends Canvas {

    private ArrayList<Policko> hraciaPlocha;

    public MojePlatno(ArrayList<Policko> hraciaPlocha){
        this.hraciaPlocha = hraciaPlocha;
    }

    @Override
    public void paint(Graphics g) {
        int index = 0;
        for(int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                Policko aktualnePolicko = hraciaPlocha.get(index);
                if (aktualnePolicko.isWall()) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * 20, y * 20, 20, 20);
                index++;
            }
        }
    }
}
