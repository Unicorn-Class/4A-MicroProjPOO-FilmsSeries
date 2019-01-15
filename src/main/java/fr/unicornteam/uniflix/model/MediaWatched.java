package fr.unicornteam.uniflix.model;


import java.util.ArrayList;
import java.util.Collections;

public class MediaWatched {
    private Media media;
    //TODO change int (number of days since watched)
    private ArrayList<Integer> view = new ArrayList<>();
    private int score;

    public MediaWatched(Media media, ArrayList<Integer> view, int score) {
        this.media = media;
        this.view = view;
        this.score = score;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public ArrayList<Integer> getTime() {
        return view;
    }

    public void setView(ArrayList<Integer> view) {
        this.view = view;
    }

    public void addWatch(int watch){this.view.add(watch);}

    public int getNbView(){return view.size();}

    public int getLastView(){
        return Collections.min(view);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Integer> getView() {
        return view;
    }
}
