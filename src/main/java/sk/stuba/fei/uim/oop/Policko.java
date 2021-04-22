package sk.stuba.fei.uim.oop;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Policko {
    private int id;
    private boolean isWall;
    private boolean isSearched;
    private boolean isBorder;
    private ArrayList<Policko> neighbours = new ArrayList<>();
    private ArrayList<ArrayList<Policko>> availableNeighbours = new ArrayList<ArrayList<Policko>>();



}
