package fr.unicornteam.uniflix.model;

import java.util.ArrayList;

public class Film extends Media {
    int id;

    public Film(int id, String title, ArrayList<String> scenarist, ArrayList<String> actor, ArrayList<String> type, ArrayList<String> director, ArrayList<String> language, ArrayList<Media> universe, ArrayList<Media> collection) {
        this.id = id;
        this.title = title;
        this.scenarist = scenarist;
        setActor(actor);
        setType(type);
        this.director = director;
        this.language = language;
        this.universe = universe;
        this.collection = collection;
    }


    /** Film(String keyword){
     String key ="8600861f4787df9fb2f5752da938b459";
     HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/search/movie?api_key="+key+"=fr-fr&query="+keyword)
     .body("{}")
     .asString();
     System.out.println(response);
     }**/
}
