package fr.unicornteam.uniflix;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Serie extends Media {
    int id;
    State currentState;
    ArrayList<Season> seasonList;
    /**Reste à fixer**/
    public Serie(String keyword) throws UnirestException, ParseException {
        this.seasonList=new ArrayList<Season>();

        /**Request to the API**/
        String key ="8600861f4787df9fb2f5752da938b459";
        HttpResponse<JsonNode> response = Unirest.get("https://api.themoviedb.org/3/search/tv?page=1&query="+keyword+"&api_key="+key).asJson();
        JSONArray res =response.getBody().getObject().getJSONArray("results");

        /**Get the main values**/
        this.id=res.getJSONObject(0).getInt("id");
        this.title=res.getJSONObject(0).getString("name");
        String date=res.getJSONObject(0).getString("first_air_date");
        this.release=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        System.out.println(res.getJSONObject(0).get("origin_country"));
        this.origin_country=res.getJSONObject(0).getJSONArray("origin_country").getString(0);
        this.overview=res.getJSONObject(0).getString("overview");


        /** Additionnal information**/
        HttpResponse<JsonNode> additionnal = Unirest.get("https://api.themoviedb.org/3/tv/"+this.id+"?api_key="+key).asJson();
        //JSONArray res2 =additionnal.getBody().getObject().getJSONArray("results");
        System.out.println(additionnal);
        /**this.scenarist=res.getJSONObject(0).getString("name");
        List<String> actor;
        List<String> type;
        Time duration;
        List<String> director;
        List<String> distributor;
        List<String> extract;
        List<String> language;
        List<Media> universe;
        List<Media> collection;
        List<Media> group;**/
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", currentState=" + currentState +
                ", seasonList=" + seasonList +
                ", title='" + title + '\'' +
                ", release=" + release +
                ", scenarist=" + scenarist +
                ", actor=" + actor +
                ", type=" + type +
                ", duration=" + duration +
                ", director=" + director +
                ", distributor=" + distributor +
                ", extract=" + extract +
                ", language=" + language +
                ", universe=" + universe +
                ", collection=" + collection +
                ", group=" + group +
                ", origin_country='" + origin_country + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }

   /*boolean AddSeason(Season){

    }*/

    public static void main(String[] args) {
        try {
            Serie s=new Serie("You");
            System.out.println(s);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}


