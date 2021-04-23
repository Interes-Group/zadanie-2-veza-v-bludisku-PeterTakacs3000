package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Okno extends JFrame implements KeyListener {

    private Random rand = new Random();
    private ArrayList<Policko> bludisko;
    private int poziciaHraca;
    private int poziciaCiela;
    private JLabel hrac;

    public Okno(){
        super();
        this.setTitle("Veza v bludisku by Peter Takacs 104604");
        this.setVisible(true);
        this.setPreferredSize(new Dimension(673,430));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HraciaPlocha plocha = new HraciaPlocha();
        plocha.vytvorBludisko();
        plocha.inicializujSusedov();
        ArrayList<Policko> bludisko = plocha.getBludisko();
        plocha.vytvorCesty(bludisko.get(21));
        this.bludisko = bludisko;
        while(true){
            int idCiela = rand.nextInt(358) + 21;
            if((!bludisko.get(idCiela).isWall()) && (!bludisko.get(idCiela).isPlayer())){
                bludisko.get(idCiela).setFinish(true);
                this.poziciaCiela = idCiela;
                break;
            }
        }
        this.poziciaHraca = 21;

        //MojePlatno platno = new MojePlatno(bludisko);
        //platno.setSize(new Dimension(400, 400));
        /////////////////////////////////////////////////////////////////////////////
        var bludiskoPanel = new JPanel();
        int index = 0;
        bludiskoPanel.setVisible(true);
        bludiskoPanel.setSize(400,400);
        bludiskoPanel.setLayout(new GridLayout(20,20));

        for(int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                Policko aktualnePolicko = bludisko.get(index);
                var policko = new JLabel("      ");
                policko.setSize(20,20);


                if (aktualnePolicko.isWall()) {
                    policko.setBackground(Color.BLACK);
                }
                else if(aktualnePolicko.isFinish()){
                    policko.setBackground(Color.RED);
                }
                else if(aktualnePolicko.isPlayer()){
                    policko.setBackground(Color.GREEN);
                    this.hrac = policko;
                }
                else {
                    policko.setBackground(Color.WHITE);
                }

                policko.setOpaque(true);
                policko.setVisible(true);
                bludiskoPanel.add(policko);
                index++;

            }
        }
        ////////////////////////////////////////////////////////////////////////////////

        var panel = new JPanel();
        panel.setLocation(400, 0);
        panel.setSize(100, 400);
        panel.setLayout(new GridLayout(2, 3));
        panel.setVisible(true);

        var gameCaountLabel = new JLabel("Vyhry: ");

        var resetButton = new JButton("Nova hra");
        var uptButton = new JButton("w");
        var downButton = new JButton("s");
        var leftButton = new JButton("a");
        var rightButton = new JButton("d");
        uptButton.addActionListener(new UpButton(this.bludisko, this.poziciaHraca, this.poziciaCiela, this.hrac));
        downButton.addActionListener(new DownButton(this.bludisko, this.poziciaHraca, this.poziciaCiela, this.hrac));
        leftButton.addActionListener(new LeftButton(this.bludisko, this.poziciaHraca, this.poziciaCiela, this.hrac));
        rightButton.addActionListener(new RightButton(this.bludisko, this.poziciaHraca, this.poziciaCiela, this.hrac));

        panel.add(gameCaountLabel);
        panel.add(uptButton);
        panel.add(resetButton);
        panel.add(leftButton);
        panel.add(downButton);
        panel.add(rightButton);

        this.add(bludiskoPanel, BorderLayout.LINE_START);
        this.add(panel, BorderLayout.LINE_END);
        this.addKeyListener(this);
        this.repaint();
        this.pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            if(!bludisko.get(poziciaHraca - 20).isWall()){
                this.hrac.setLocation(hrac.getX(), hrac.getY() - 19);
                bludisko.get(poziciaHraca - 20).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca -= 20;
            }
        }
        else if(e.getKeyChar() == 'a'){
            if(!bludisko.get(poziciaHraca - 1).isWall()){
                this.hrac.setLocation(hrac.getX() - 20, hrac.getY());
                bludisko.get(poziciaHraca - 1).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca -= 1;
            }
        }
        else if(e.getKeyChar() == 's'){
            if(!bludisko.get(poziciaHraca + 20).isWall()){
                this.hrac.setLocation(hrac.getX(), hrac.getY() + 19);
                bludisko.get(poziciaHraca + 20).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca += 20;
            }
        }
        else if(e.getKeyChar() == 'd'){
            if(!bludisko.get(poziciaHraca + 1).isWall()){
                this.hrac.setLocation(hrac.getX() + 20, hrac.getY());
                bludisko.get(poziciaHraca + 1).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca += 1;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

