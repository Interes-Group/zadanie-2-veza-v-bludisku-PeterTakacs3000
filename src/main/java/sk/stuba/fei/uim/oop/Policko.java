package sk.stuba.fei.uim.oop;

import lombok.Data;

@Data
public class Policko {
    private int x;
    private int y;
    private boolean isWall;
    private Policko topNeighbour = null;
    private Policko bottomNeighbour = null;
    private Policko leftNeighbour = null;
    private Policko rightNeighbour = null;

    public Policko(int x, int y){
        this.x = x;
        this.y = y;
    }

}
