package fr.unicornteam.uniflix;

import com.mashape.unirest.http.Unirest;
import com.sun.deploy.net.HttpResponse;

import java.net.URL;
import java.sql.Time;
import java.sql.Date;
import java.util.List;

public abstract class Media {
    String title;
    Date release;
    List<String> actor;
    List<String> type;
    Time duration;
    List<String> director;
    List<String> distributor;
    List<String> extract;
    List<String> language;
    List<Media> universe;
    List<Media> collection;
    List<Media> group;

   /** Media(String keyword){
        String key ="8600861f4787df9fb2f5752da938b459";
        HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/search/movie?api_key="+key+"=fr-fr&query="+keyword)
                .body("{}")
                .asString();
        System.out.println(response);
    }**/
}
