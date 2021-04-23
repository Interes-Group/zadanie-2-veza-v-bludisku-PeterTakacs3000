package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MojePlatno extends JPanel implements MouseListener, MouseMotionListener {

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
                var policko = new JLabel();

                if (aktualnePolicko.isWall()) {
                    policko.setBackground(Color.BLACK);
                }
                else if(aktualnePolicko.isFinish()){
                    policko.setBackground(Color.RED);
                }
                else {
                    policko.setBackground(Color.WHITE);
                }
                policko.addMouseListener(this);
                policko.addMouseMotionListener(this);
                policko.setOpaque(true);
                policko.setVisible(true);
                this.add(policko);
                index++;

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
