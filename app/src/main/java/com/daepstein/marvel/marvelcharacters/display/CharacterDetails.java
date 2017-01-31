package com.daepstein.marvel.marvelcharacters.display;

import com.daepstein.marvel.marvelcharacters.model.CharacterModel;

import java.util.Observable;

/**
 * Created by daepstein on 1/31/2017.
 */

public class CharacterDetails extends Observable{


    CharacterModel cm;
    public CharacterDetails()
    {
        cm = null;
    }

    public CharacterModel getCm() {
        return cm;
    }

    public void setCm(CharacterModel cm) {
        this.cm = cm;
        setChanged();
        notifyObservers(cm);
    }

}
