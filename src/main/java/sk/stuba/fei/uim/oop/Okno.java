package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Okno extends JFrame implements KeyListener, ActionListener, MouseListener, MouseMotionListener {

    private volatile boolean koniecHry = false;
    private int pocitadloVyhier = 0;
    private Random rand = new Random();
    private ArrayList<Policko> bludisko;
    private int poziciaHraca;
    private int poziciaCiela;
    private JLabel hrac;
    private JLabel gameCaountLabel = new JLabel("Vyhry: ");
    private JButton resetButton = new JButton("Nova hra");
    private JButton uptButton = new JButton("w");
    private JButton downButton = new JButton("s");
    private JButton leftButton = new JButton("a");
    private JButton rightButton = new JButton("d");
    private JPanel bludiskoPanel = new JPanel();
    private JPanel panel = new JPanel();



    public Okno(int pocetVyhier){
        super();
            this.pocitadloVyhier = pocetVyhier;

            this.setTitle("Veza v bludisku by Peter Takacs 104604");
            this.setVisible(true);
            this.setPreferredSize(new Dimension(673, 430));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            HraciaPlocha plocha = new HraciaPlocha();

            plocha.vytvorBludisko();
            plocha.inicializujSusedov();
            ArrayList<Policko> bludisko = plocha.getBludisko();
            plocha.vytvorCesty(bludisko.get(21));
            this.bludisko = bludisko;
            while (true) {
                int idCiela = rand.nextInt(358) + 21;
                if ((!bludisko.get(idCiela).isWall()) && (!bludisko.get(idCiela).isPlayer())) {
                    bludisko.get(idCiela).setFinish(true);
                    this.poziciaCiela = idCiela;
                    break;
                }
            }
            this.poziciaHraca = 21;

            int index = 0;
            bludiskoPanel.setVisible(true);
            bludiskoPanel.setSize(400, 400);
            bludiskoPanel.setLayout(new GridLayout(20, 20));
            bludiskoPanel.addMouseListener(this);

            for (int x = 0; x < 20; x++) {
                for (int y = 0; y < 20; y++) {
                    Policko aktualnePolicko = bludisko.get(index);
                    var policko = new JLabel("      ");
                    policko.setSize(20, 20);


                    if (aktualnePolicko.isWall()) {
                        policko.setBackground(Color.BLACK);
                    } else if (aktualnePolicko.isFinish()) {
                        policko.setBackground(Color.RED);
                    } else if (aktualnePolicko.isPlayer()) {
                        policko.setBackground(Color.GREEN);
                        this.hrac = policko;
                    } else {
                        policko.setBackground(Color.WHITE);
                    }

                    policko.addMouseMotionListener(this);
                    policko.setOpaque(true);
                    policko.setVisible(true);
                    bludiskoPanel.add(policko);
                    index++;

                }
            }

            panel.setLocation(400, 0);
            panel.setSize(100, 400);
            panel.setLayout(new GridLayout(2, 3));
            panel.setVisible(true);



            uptButton.addActionListener(this);
            downButton.addActionListener(this);
            leftButton.addActionListener(this);
            rightButton.addActionListener(this);
            resetButton.addActionListener(this);
            uptButton.setFocusable(false);
            downButton.setFocusable(false);
            leftButton.setFocusable(false);
            rightButton.setFocusable(false);
            resetButton.setFocusable(false);

            this.gameCaountLabel.setText(this.gameCaountLabel.getText() + pocitadloVyhier);

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
                if(this.poziciaHraca == this.poziciaCiela){
                    pocitadloVyhier++;
                    this.gameCaountLabel.setText("Vyhry: " + this.pocitadloVyhier);
                    Okno okno = new Okno(pocitadloVyhier);
                }
            }
        }
        else if(e.getKeyChar() == 'a'){
            if(!bludisko.get(poziciaHraca - 1).isWall()){
                this.hrac.setLocation(hrac.getX() - 20, hrac.getY());
                bludisko.get(poziciaHraca - 1).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca -= 1;
                if(poziciaHraca == poziciaCiela){
                    pocitadloVyhier++;
                    this.gameCaountLabel.setText("Vyhry: " + this.pocitadloVyhier);
                    Okno okno = new Okno(pocitadloVyhier);
                }
            }
        }
        else if(e.getKeyChar() == 's'){
            if(!bludisko.get(poziciaHraca + 20).isWall()){
                this.hrac.setLocation(hrac.getX(), hrac.getY() + 19);
                bludisko.get(poziciaHraca + 20).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca += 20;
                if(poziciaHraca == poziciaCiela){
                    pocitadloVyhier++;
                    this.gameCaountLabel.setText("Vyhry: " + this.pocitadloVyhier);
                    Okno okno = new Okno(pocitadloVyhier);
                }
            }
        }
        else if(e.getKeyChar() == 'd'){
            if(!bludisko.get(poziciaHraca + 1).isWall()){
                this.hrac.setLocation(hrac.getX() + 20, hrac.getY());
                bludisko.get(poziciaHraca + 1).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca += 1;
                if(poziciaHraca == poziciaCiela){
                    pocitadloVyhier++;
                    this.gameCaountLabel.setText("Vyhry: " + this.pocitadloVyhier);
                    Okno okno = new Okno(pocitadloVyhier);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.uptButton){
            if(!bludisko.get(poziciaHraca - 20).isWall()){
                this.hrac.setLocation(hrac.getX(), hrac.getY() - 19);
                bludisko.get(poziciaHraca - 20).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca -= 20;
                if(poziciaHraca == poziciaCiela){
                    pocitadloVyhier++;
                    this.gameCaountLabel.setText("Vyhry: " + this.pocitadloVyhier);
                    Okno okno = new Okno(pocitadloVyhier);
                }
            }
        }
        else if(e.getSource() == this.downButton){
            if(!bludisko.get(poziciaHraca + 20).isWall()){
                this.hrac.setLocation(hrac.getX(), hrac.getY() + 19);
                bludisko.get(poziciaHraca + 20).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca += 20;
                if(poziciaHraca == poziciaCiela){
                    pocitadloVyhier++;
                    this.gameCaountLabel.setText("Vyhry: " + this.pocitadloVyhier);
                    Okno okno = new Okno(pocitadloVyhier);
                }
            }
        }
        else if(e.getSource() == this.leftButton){
            if(!bludisko.get(poziciaHraca - 1).isWall()){
                this.hrac.setLocation(hrac.getX() - 20, hrac.getY());
                bludisko.get(poziciaHraca - 1).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca -= 1;
                if(poziciaHraca == poziciaCiela){
                    pocitadloVyhier++;
                    this.gameCaountLabel.setText("Vyhry: " + this.pocitadloVyhier);
                    Okno okno = new Okno(pocitadloVyhier);
                }
            }
        }
        else if(e.getSource() == this.rightButton){
            if(!bludisko.get(poziciaHraca + 1).isWall()) {
                this.hrac.setLocation(hrac.getX() + 20, hrac.getY());
                bludisko.get(poziciaHraca + 1).setPlayer(true);
                bludisko.get(poziciaHraca).setPlayer(false);
                poziciaHraca += 1;
                if(poziciaHraca == poziciaCiela){
                    pocitadloVyhier++;
                    this.gameCaountLabel.setText("Vyhry: " + this.pocitadloVyhier);
                    Okno okno = new Okno(pocitadloVyhier);
                }
            }
        }
        else if(e.getSource() == this.resetButton){
            Okno okno = new Okno(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println(mouseX + " " + mouseY);
        System.out.println(this.hrac.getX() + " " + this.hrac.getY());
        if((mouseX > this.hrac.getX()) && (mouseX < this.hrac.getX() + 20)){
            if((mouseY > this.hrac.getY()) && (mouseY < this.hrac.getY() + 20)){
                this.hrac.setBackground(Color.YELLOW);
            }

            /*
            Policko aktualnePolicko = this.bludisko.get(this.poziciaHraca);
            int aktualnaPozicia = poziciaHraca;
            while (!aktualnePolicko.isWall()){
                aktualnePolicko = this.bludisko.get(aktualnaPozicia);
                aktualnePolicko.setReachable(true);
                aktualnaPozicia++;
            }
            aktualnaPozicia = poziciaHraca;
            while (!aktualnePolicko.isWall()){
                aktualnePolicko = this.bludisko.get(aktualnaPozicia);
                aktualnePolicko.setReachable(true);
                aktualnaPozicia--;
            }
            aktualnaPozicia = poziciaHraca;
            while (!aktualnePolicko.isWall()){
                aktualnePolicko = this.bludisko.get(aktualnaPozicia);
                aktualnePolicko.setReachable(true);
                aktualnaPozicia += 20;
            }
            aktualnaPozicia = poziciaHraca;
            while (!aktualnePolicko.isWall()){
                aktualnePolicko = this.bludisko.get(aktualnaPozicia);
                aktualnePolicko.setReachable(true);
                aktualnaPozicia -= 20;
            }

             */
        }
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

