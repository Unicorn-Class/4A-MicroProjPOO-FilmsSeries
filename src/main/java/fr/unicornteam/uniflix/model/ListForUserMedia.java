package fr.unicornteam.uniflix.model;

import java.util.ArrayList;

public abstract class ListForUserMedia {

    ArrayList<String> type = new ArrayList<>();
    ArrayList<String> actor = new ArrayList<>();


    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public void addType(String type) {
        this.type.add(type);
    }

    public ArrayList<String> getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        this.actor = actor;
    }

    public void addActor(String actor) {
        this.actor.add(actor);
    }

}
