package fr.unicornteam.uniflix.model;

import java.util.ArrayList;

public abstract class ListForUserMedia {

    private ArrayList<String> type = new ArrayList<>();
    private ArrayList<String> actor = new ArrayList<>();


    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public ArrayList<String> getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        this.actor = actor;
    }

}
