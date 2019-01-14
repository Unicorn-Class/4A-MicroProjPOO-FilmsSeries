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

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", listeMovie=" + listeMovie +
                ", title='" + title + '\'' +
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
                '}';
    }

    public Film(String keyMovie) throws ParseException, UnirestException {

        /**Get the movie**/
        keyMovie=keyMovie.replaceAll(" ","%20");
        String key="8600861f4787df9fb2f5752da938b459";
        HttpResponse<JsonNode> response = Unirest.get("https://api.themoviedb.org/3/search/movie?api_key="+key+"&language=en-US&query="+keyMovie).asJson();
        JSONArray rs =response.getBody().getObject().getJSONArray("results");
        this.id=rs.getJSONObject(0).getInt("id");
        this.title=rs.getJSONObject(0).getString("original_title");
        String date=rs.getJSONObject(0).getString("release_date");
        this.release=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.overview=rs.getJSONObject(0).getString("overview");

        /**Get additionnal information**/
        HttpResponse<JsonNode> additionnal = Unirest.get("https://api.themoviedb.org/3/movie/"+this.id+"?api_key="+key).asJson();
        JSONObject res2 =additionnal.getBody().getObject();


        ArrayList<String> t=new ArrayList<String>();
        for (int i =0;i<res2.getJSONArray("genres").length();i++){
            t.add(res2.getJSONArray("genres").getJSONObject(i).getString("name"));
        }

        this.setType(t);
        this.origin_country=res2.getJSONArray("production_countries").getJSONObject(0).getString("name");
        this.averageScore=(float)res2.getDouble("vote_average");
        this.img="https://image.tmdb.org/t/p/w1280"+res2.getString("backdrop_path");


        HttpResponse<JsonNode> ensemble=Unirest.get("https://api.themoviedb.org/3/movie/"+this.id+"/credits?api_key="+key).asJson();
        JSONObject ens=ensemble.getBody().getObject();

        ArrayList<String> act=new ArrayList<String>();
        for(int i=0;i<ens.getJSONArray("cast").length();i++){
            act.add(ens.getJSONArray("cast").getJSONObject(i).getString("name"));
        }
        
        this.setActor(act);

        this.scenarist=new ArrayList<String>();
        this.director=new ArrayList<String>();

        for (int i =0;i<ens.getJSONArray("crew").length();i++) {
            String job = ens.getJSONArray("crew").getJSONObject(i).getString("job");
            String member = ens.getJSONArray("crew").getJSONObject(i).getString("name");
            if(job.equalsIgnoreCase("Director")){
             this.director.add(member);
             }
             else if(job.equalsIgnoreCase("Writer")){
             this.scenarist.add(member);
             }
        }
        this.distributor=new ArrayList<String>();
        for (int i =0;i<res2.getJSONArray("production_companies").length();i++){
            this.distributor.add(res2.getJSONArray("production_companies").getJSONObject(i).getString("name"));
        }
        /**Recupération des extraits**/
            HttpResponse<JsonNode> extract = Unirest.get("https://api.themoviedb.org/3/movie/" + this.id + "/videos?api_key=" + key).asJson();
            JSONObject res3 = extract.getBody().getObject();
            this.extract = new ArrayList<String>();

            for (int i = 0; i < res3.getJSONArray("results").length(); i++) {
                this.extract.add(res3.getJSONArray("results").getJSONObject(i).getString("key"));
            }
    }


    public static void main(String[] args) {
        try {
            Film s=new Film("Moi moche et méchant");
            System.out.println(s);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
