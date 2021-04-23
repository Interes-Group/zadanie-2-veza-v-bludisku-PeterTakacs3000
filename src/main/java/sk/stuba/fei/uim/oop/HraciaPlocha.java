package sk.stuba.fei.uim.oop;

import lombok.Data;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

@Data
public class HraciaPlocha {
    private ArrayList<Policko> bludisko = new ArrayList<>();
    private Random rand = new Random();

    public void vytvorBludisko(){
        int idPolicka = 0;
        for(int x = 0; x < 20; x++) {
            for(int y = 0; y < 20; y++) {
                Policko novePolicko = new Policko();
                novePolicko.setId(idPolicka);
                novePolicko.setWall(true);
                novePolicko.setSearched(false);
                novePolicko.setBorder((x == 0) || (y == 0) || (x == 19) || (y == 19));
                novePolicko.setSearched((x == 0) || (y == 0) || (x == 19) || (y == 19));

                this.bludisko.add(novePolicko);
                idPolicka++;
            }
        }
        bludisko.get(21).setPlayer(true);
    }

    public void inicializujSusedov(){

        for(int i = 21; i < 379; i++) {
            Policko aktualnePolicko = this.bludisko.get(i);
            if(!aktualnePolicko.isBorder()){
                Policko lavySused = this.bludisko.get(i-1);
                Policko pravySused = this.bludisko.get(i+1);
                Policko hornySused = this.bludisko.get(i-20);
                Policko dolnySused = this.bludisko.get(i+20);
                if(!lavySused.isBorder()) {
                    Policko dalsyLavySused = this.bludisko.get(i-2);
                    aktualnePolicko.getNeighbours().add(lavySused);
                    if(!dalsyLavySused.isBorder()){
                        ArrayList<Policko> dosiahnutelniSusedia1 = new ArrayList<>();
                        dosiahnutelniSusedia1.add(new Policko());
                        dosiahnutelniSusedia1.add(new Policko());
                        dosiahnutelniSusedia1.set(0, lavySused);
                        dosiahnutelniSusedia1.set(1, dalsyLavySused);
                        aktualnePolicko.getAvailableNeighbours().add(dosiahnutelniSusedia1);
                    }
                }
                if(!pravySused.isBorder()) {
                    Policko dalsyPravySused = this.bludisko.get(i+2);
                    aktualnePolicko.getNeighbours().add(pravySused);
                    if(!dalsyPravySused.isBorder()){
                        ArrayList<Policko> dosiahnutelniSusedia2 = new ArrayList<>();
                        dosiahnutelniSusedia2.add(new Policko());
                        dosiahnutelniSusedia2.add(new Policko());
                        dosiahnutelniSusedia2.set(0, pravySused);
                        dosiahnutelniSusedia2.set(1, dalsyPravySused);
                        aktualnePolicko.getAvailableNeighbours().add(dosiahnutelniSusedia2);
                    }
                }
                if(!hornySused.isBorder()) {
                    Policko dalsyHornySused = this.bludisko.get(i-40);
                    aktualnePolicko.getNeighbours().add(hornySused);
                    if(!dalsyHornySused.isBorder()){
                        ArrayList<Policko> dosiahnutelniSusedia3 = new ArrayList<>();
                        dosiahnutelniSusedia3.add(new Policko());
                        dosiahnutelniSusedia3.add(new Policko());
                        dosiahnutelniSusedia3.set(0, hornySused);
                        dosiahnutelniSusedia3.set(1, dalsyHornySused);
                        aktualnePolicko.getAvailableNeighbours().add(dosiahnutelniSusedia3);
                    }
                }
                if(!dolnySused.isBorder()) {
                    Policko dalsyDolnySused = this.bludisko.get(i+40);
                    aktualnePolicko.getNeighbours().add(dolnySused);
                    if(!dalsyDolnySused.isBorder()){
                        ArrayList<Policko> dosiahnutelniSusedia4 = new ArrayList<>();
                        dosiahnutelniSusedia4.add(new Policko());
                        dosiahnutelniSusedia4.add(new Policko());
                        dosiahnutelniSusedia4.set(0, dolnySused);
                        dosiahnutelniSusedia4.set(1, dalsyDolnySused);
                        aktualnePolicko.getAvailableNeighbours().add(dosiahnutelniSusedia4);
                    }
                }
            }
        }
    }

    public void vytvorCesty(Policko aktPolicko){
        Policko sused1 = new Policko();
        Policko sused2 = new Policko();
        Policko naslPolicko;
        Policko dalsieAktPolicko;
        int cisloSuseda = 0;
        int pocetSusedov;
        int i = 0;

        aktPolicko.setSearched(true);


        while(i < aktPolicko.getAvailableNeighbours().size()){
            if((aktPolicko.getAvailableNeighbours().get(i).get(0).isSearched()) || (aktPolicko.getAvailableNeighbours().get(i).get(1).isSearched())){
                aktPolicko.getAvailableNeighbours().remove(i);
            }else{
                i++;
            }
        }
        pocetSusedov = aktPolicko.getAvailableNeighbours().size();
        if(pocetSusedov > 1) {
            cisloSuseda = rand.nextInt(pocetSusedov);
        }

        if(pocetSusedov < 1){
            return;
        }
        naslPolicko = aktPolicko.getAvailableNeighbours().get(cisloSuseda).get(0);
        dalsieAktPolicko = aktPolicko.getAvailableNeighbours().get(cisloSuseda).get(1);

            if (abs(naslPolicko.getId() - aktPolicko.getId()) == 1) {
                sused1 = this.bludisko.get(naslPolicko.getId() + 20);
                sused2 = this.bludisko.get(naslPolicko.getId() - 20);
            } else if (abs(naslPolicko.getId() - aktPolicko.getId()) == 20) {
                sused1 = this.bludisko.get(naslPolicko.getId() + 1);
                sused2 = this.bludisko.get(naslPolicko.getId() - 1);
            }
            while(true) {
                sused1.setSearched(true);
                sused2.setSearched(true);
                naslPolicko.setSearched(true);
                aktPolicko.setWall(false);
                naslPolicko.setWall(false);
                dalsieAktPolicko.setWall(false);
                vytvorCesty(dalsieAktPolicko);
                if(aktPolicko.getAvailableNeighbours().size() == 0){
                    return;
                }
                i = 0;
                while(i < aktPolicko.getAvailableNeighbours().size()){
                    if((aktPolicko.getAvailableNeighbours().get(i).get(0).isSearched()) || (aktPolicko.getAvailableNeighbours().get(i).get(1).isSearched())){
                        aktPolicko.getAvailableNeighbours().remove(i);
                    }else{
                        i++;
                    }
                }
                pocetSusedov = aktPolicko.getAvailableNeighbours().size();

                if (pocetSusedov > 1) {
                    cisloSuseda = rand.nextInt(pocetSusedov);
                } else {
                    cisloSuseda = 0;
                }

                if(pocetSusedov < 1){
                    return;
                }
                naslPolicko = aktPolicko.getAvailableNeighbours().get(cisloSuseda).get(0);
                dalsieAktPolicko = aktPolicko.getAvailableNeighbours().get(cisloSuseda).get(1);
            }

    }
}
