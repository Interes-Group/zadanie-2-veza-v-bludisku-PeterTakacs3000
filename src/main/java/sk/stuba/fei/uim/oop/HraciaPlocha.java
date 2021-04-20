package sk.stuba.fei.uim.oop;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HraciaPlocha {
    private ArrayList<Policko> bludisko = new ArrayList();

    public void vytvorBludisko(){
        for(int x = 0; x < 11; x++) {
            for(int y = 0; y < 11; y++) {
                Policko novePolicko = new Policko(x, y);
                novePolicko.setWall(true);
                this.bludisko.add(novePolicko);
            }
        }
    }
}
