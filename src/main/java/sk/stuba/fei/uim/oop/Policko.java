package sk.stuba.fei.uim.oop;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Policko {
    private int x;
    private int y;
    private int id;
    private boolean isReachable;
    private boolean isSelected;
    private boolean isWall;
    private boolean isPlayer;
    private boolean isFinish;
    private boolean isSearched;
    private boolean isBorder;
    private ArrayList<Policko> neighbours = new ArrayList<>();
    private ArrayList<ArrayList<Policko>> availableNeighbours = new ArrayList<>();



}
