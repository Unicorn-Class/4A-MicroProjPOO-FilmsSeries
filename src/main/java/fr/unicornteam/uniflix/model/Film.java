package fr.unicornteam.uniflix.model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Entity
public class Film extends Media {
    @Id
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


    ArrayList<Film> listeMovie=new ArrayList<Film>();
    public Film(String keyMovie) throws ParseException, UnirestException {

        String key="8600861f4787df9fb2f5752da938b459";
        //HttpResponse<JsonNode> response = Unirest.get("https://api.themoviedb.org/3/search/movie?api_key="+key+"=fr-fr&query="+keyMovie).asJson();
        HttpResponse<JsonNode> response = Unirest.get("https://api.themoviedb.org/3/search/movie?api_key="+key+"&language=en-US&query="+keyMovie).asJson();
        JSONArray rs =response.getBody().getObject().getJSONArray("results");
        System.out.println(rs);
        this.id=rs.getJSONObject(0).getInt("id");
        this.title=rs.getJSONObject(0).getString("original_title");
        String date=rs.getJSONObject(0).getString("release_date");
        this.release=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.overview=rs.getJSONObject(0).getString("overview");


        HttpResponse<JsonNode> additionnal = Unirest.get("https://api.themoviedb.org/3/movie/"+this.id+"?api_key="+key).asJson();
        JSONObject res2 =additionnal.getBody().getObject();
        //System.out.println(res2);

        ArrayList<String> t=new ArrayList<String>();
        for (int i =0;i<res2.getJSONArray("genres").length();i++){
            t.add(res2.getJSONArray("genres").getJSONObject(i).getString("name"));
        }

        this.setType(t);
        //System.out.println(res2.getJSONArray("genres").getJSONObject(0).getString("name"));
        this.origin_country=res2.getJSONArray("production_countries").getJSONObject(0).getString("name");
        //this.actor=new ArrayList<String>();


        HttpResponse<JsonNode> ensemble=Unirest.get("https://api.themoviedb.org/3/movie/"+this.id+"/credits?api_key="+key).asJson();
        JSONObject ens=ensemble.getBody().getObject();

        ArrayList<String> act=new ArrayList<String>();
        System.out.println(ens);
        System.out.println(ens.getJSONArray("cast").getJSONObject(0).getString("name"));
        for(int i=0;i<ens.getJSONArray("cast").length();i++){
            act.add(ens.getJSONArray("cast").getJSONObject(i).getString("name"));
        }
        
        this.setActor(act);

    }

    /** Film(String keyword){
     String key ="8600861f4787df9fb2f5752da938b459";
     HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/search/movie?api_key="+key+"=fr-fr&query="+keyword)
     .body("{}")
     .asString();
     System.out.println(response);
     }**/

    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release=" + release +



                ", origin_country='" + origin_country + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }

    public static void main(String[] args) {
        try {
            Film s=new Film("BLACK");
            System.out.println(s);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
