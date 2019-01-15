package fr.unicornteam.uniflix.model;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.Entity;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Serie extends Media {
    int id;
    String currentState;
    int numberOfSeason;
    ArrayList<Season> seasonList;

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", currentState='" + currentState + '\'' +
                ", numberOfSeason=" + numberOfSeason +
                ", seasonList=" + seasonList +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", release=" + release +
                ", scenarist=" + scenarist +
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
                ", averageScore=" + averageScore +
                ", type=" + type +
                ", actor=" + actor +
                /*", mediaSuggestion=" + mediaSuggestion +
                ", userSuggestion=" + userSuggestion +*/
                '}';
    }

    public Serie(String keyword) throws UnirestException, ParseException {
        keyword=keyword.replaceAll(" ","%20");
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
        JSONObject res2 =additionnal.getBody().getObject();
        this.scenarist=new ArrayList<String>();
        for (int i =0;i<res2.getJSONArray("created_by").length();i++){
            this.scenarist.add(res2.getJSONArray("created_by").getJSONObject(i).getString("name"));
        }
        setType(new ArrayList<String>());
        for (int i =0;i<res2.getJSONArray("genres").length();i++){
            addType(res2.getJSONArray("genres").getJSONObject(i).getString("name"));
        }
        this.currentState= res2.getString("status");
        this.distributor=new ArrayList<String>();
        for (int i =0;i<res2.getJSONArray("networks").length();i++){
            this.distributor.add(res2.getJSONArray("networks").getJSONObject(i).getString("name"));
        }
        this.director=new ArrayList<String>();
        for (int i =0;i<res2.getJSONArray("production_companies").length();i++){
            this.director.add(res2.getJSONArray("production_companies").getJSONObject(i).getString("name"));
        }

        this.numberOfSeason=res2.getInt("number_of_seasons");
        this.seasonList=new ArrayList<Season>();
        for (int i =1;i<=this.numberOfSeason;i++){
            Season s =new Season(this.id,i);
            seasonList.add(s);

        }
        this.distributor=new ArrayList<String>();
        for (int i =0;i<res2.getJSONArray("production_companies").length();i++){
            this.distributor.add(res2.getJSONArray("production_companies").getJSONObject(i).getString("name"));
        }
        this.averageScore=(float)res2.getDouble("vote_average");
        this.img="https://image.tmdb.org/t/p/w1280"+res2.getString("backdrop_path");

        /**Recupération des extraits**/
        HttpResponse<JsonNode> extract = Unirest.get("https://api.themoviedb.org/3/tv/"+this.id+"/videos?api_key="+key).asJson();
        JSONObject res3 =extract.getBody().getObject();
        this.extract=new ArrayList<String>();

        for (int i =0;i<res3.getJSONArray("results").length();i++){
            this.extract.add(res3.getJSONArray("results").getJSONObject(i).getString("key"));
        }
        //this.language= (String[]) res2.get("languages");


    }


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public int getNumberOfSeason() {
        return numberOfSeason;
    }

    public void setNumberOfSeason(int numberOfSeason) {
        this.numberOfSeason = numberOfSeason;
    }

    public ArrayList<Season> getSeasonList() {
        return seasonList;
    }

    public void setSeasonList(ArrayList<Season> seasonList) {
        this.seasonList = seasonList;
    }
}


