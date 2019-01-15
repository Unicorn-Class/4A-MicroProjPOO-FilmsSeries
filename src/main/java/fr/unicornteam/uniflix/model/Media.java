package fr.unicornteam.uniflix.model;

import fr.unicornteam.uniflix.UtilNico.UtilNico;
import fr.unicornteam.uniflix.model.Suggestion.MediaSuggest;
import fr.unicornteam.uniflix.model.Suggestion.MediaSuggestion;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public abstract class Media extends CommonForUserMedia {
    @Id
    int id;
    String title;
    String img;
    Date release;
    ArrayList<String> scenarist;
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
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://145.239.222.239:3306/uniflix?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    static final String USER = "uniflix";
    static final String PASS = "unicorn.class";
    public String getOverview() {
        return overview;
    }


    //TODO WARNING toRemove
    double averageScore;

    public int getId(){return id;}
    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public List<String> getScenarist() {
        return scenarist;
    }

    public void setScenarist(ArrayList<String> scenarist) {
        this.scenarist = scenarist;
    }

    public void addScenarist(String scenarist) {
        this.scenarist.add(scenarist);
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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(ArrayList<String> director) {
        this.director = director;
    }

    public void addDirector(String director) {
        this.director.add(director);
    }

    public List<String> getDistributor() {
        return distributor;
    }

    public void setDistributor(ArrayList<String> distributor) {
        this.distributor = distributor;
    }

    public void addDistributor(String distributor) {
        this.distributor.add(distributor);
    }

    public List<String> getExtract() {
        return extract;
    }

    public void setExtract(ArrayList<String> extract) {
        this.extract = extract;
    }

    public void addExtract(String extract) {
        this.extract.add(extract);
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(ArrayList<String> language) {
        this.language = language;
    }

    public void addLanguage(String language) {
        this.language.add(language);
    }

    public List<Media> getUniverse() {
        return universe;
    }

    public void setUniverse(ArrayList<Media> universe) {
        this.universe = universe;
    }

    public void addUniverse(Media universe) {
        this.universe.add(universe);
    }

    public List<Media> getCollection() {
        return collection;
    }

    public void setCollection(ArrayList<Media> collection) {
        this.collection = collection;
    }

    public void addCollection(Media collection) {
        this.collection.add(collection);
    }

    public List<Media> getGroup() {
        return group;
    }

    public void setGroup(ArrayList<Media> group) {
        this.group = group;
    }

    public void addGroup(Media group) {
        this.group.add(group);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "Media{" +
                "title='" + title + '\'' +
                ", actor=" + getActor() +
                ", type=" + getType() +
                '}';
    }

    public boolean hadInUniverse(Media m){
        for(Media mU : universe){
            if(mU.equals(m)){
                return true;
            }
        }
        return false;
    }

    public boolean hadInCollection(Media m){
        for(Media mC : collection){
            if(mC.equals(m)){
                return true;
            }
        }
        return false;
    }



    public ArrayList<Media> getSuggestionMedia(int nb){
        ArrayList<Media> list = new ArrayList<>();
        ArrayList<MediaSuggest> mediasSuggest = MediaSuggestion.getSuggestionMedia(this, Util.allMedia);

        int limit = nb;
        if(nb>mediasSuggest.size()){
            limit = mediasSuggest.size();
        }

        for(int i=0 ; i<limit ; i++) {
            list.add(mediasSuggest.get(i).getMedia());
        }
        return list;
    }



    public ArrayList<Media> ucMedia(){
        ArrayList<Media> listeCU=new ArrayList<Media>();

        listeCU.addAll(getCollection());
        listeCU.addAll(getUniverse());
        return listeCU;
    }



}
