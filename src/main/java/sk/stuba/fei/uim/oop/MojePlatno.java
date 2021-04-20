package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.util.ArrayList;

public class MojePlatno extends Canvas {

    private ArrayList<Policko> hraciaPlocha = new ArrayList();

    public MojePlatno(ArrayList hraciaPlocha){
        this.hraciaPlocha = hraciaPlocha;
    }

    @Override
    public void paint(Graphics g) {
        int index = 0;
        for(int x = 0; x < 11; x++){
            for(int y = 0; y < 11; y++){
                Policko aktualnePolicko = hraciaPlocha.get(index);
                if(aktualnePolicko.isWall()){
                    g.setColor(Color.BLACK);
                }
                else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x*30, y*30, 30, 30);
                index++;
            }
        }
        index = 0;
    }
}
