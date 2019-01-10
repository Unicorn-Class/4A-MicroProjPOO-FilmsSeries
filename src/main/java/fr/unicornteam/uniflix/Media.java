package fr.unicornteam.uniflix;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public abstract class Media {
    String title;
    Date release;
    ArrayList<String>  scenarist;
    ArrayList<String> actor;
    ArrayList<String> type;
    Time duration;
    ArrayList<String> director;
    ArrayList<String> distributor;
    ArrayList<String> extract;
    ArrayList<String> language;
    ArrayList<Media> universe;
    ArrayList<Media> collection;
    ArrayList<Media> group;
    String origin_country;
    String overview;

    //WARNING toRemove
    float averageScore;

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public List<String> getScenarist() {
        return scenarist;
    }

    public void setScenarist(List<String> scenarist) {
        this.scenarist = scenarist;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public List<String> getActor() {
        return actor;
    }

    public void setActor(List<String> actor) {
        this.actor = actor;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getDistributor() {
        return distributor;
    }

    public void setDistributor(List<String> distributor) {
        this.distributor = distributor;
    }

    public List<String> getExtract() {
        return extract;
    }

    public void setExtract(List<String> extract) {
        this.extract = extract;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public List<Media> getUniverse() {
        return universe;
    }

    public void setUniverse(List<Media> universe) {
        this.universe = universe;
    }

    public List<Media> getCollection() {
        return collection;
    }

    public void setCollection(List<Media> collection) {
        this.collection = collection;
    }

    public List<Media> getGroup() {
        return group;
    }

    public void setGroup(List<Media> group) {
        this.group = group;
    }


}
