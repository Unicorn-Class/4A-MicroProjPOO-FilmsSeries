package fr.unicornteam.uniflix.model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Episode extends Media{
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", release=" + releaseDate +
                ", scenarist=" + scenarist +
                ", duration=" + duration +
                ", director=" + director +
                ", distributor=" + distributor +
                ", extract=" + extract +
                ", language=" + language +
                ", universe=" + universe +
                ", collection=" + collection +
                ", group=" + groupMedia +
                ", origin_country='" + origin_country + '\'' +
                ", overview='" + overview + '\'' +
                ", averageScore=" + averageScore +
                ", type=" + type +
                ", actor=" + actor +
                /*", mediaSuggestion=" + mediaSuggestion +
                ", userSuggestion=" + userSuggestion +*/
                '}';
    }

    public Episode (int tv_id, int season_number, int episode_number) throws UnirestException,ParseException {
        /**Request to the API**/
        String key ="8600861f4787df9fb2f5752da938b459";
        HttpResponse<JsonNode> response = Unirest.get("https://api.themoviedb.org/3/tv/"+tv_id+"/season/"+season_number+"/episode/"+episode_number+"?api_key="+key).asJson();
        JSONObject res =response.getBody().getObject();

        /**Get the main values**/
        this.id=res.getInt("id");
        String date=res.getString("air_date");
        this.releaseDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.title=res.getString("name");
        this.overview=res.getString("overview");
        this.scenarist=new ArrayList<String>();
        this.director=new ArrayList<String>();
        for (int i =0;i<res.getJSONArray("crew").length();i++){
            String job=res.getJSONArray("crew").getJSONObject(i).getString("job");
            String member=res.getJSONArray("crew").getJSONObject(i).getString("name");
            if(job.equalsIgnoreCase("Director")){
                this.director.add(member);
            }
            else if(job.equalsIgnoreCase("Writer")){
                this.scenarist.add(member);
            }
        }

        this.averageScore=(float)res.getDouble("vote_average");
        /**Get the cast**/
        HttpResponse<JsonNode> res2 = Unirest.get("https://api.themoviedb.org/3/tv/"+tv_id+"/season/"+season_number+"/episode/"+episode_number+"/credits?api_key="+key).asJson();
        JSONArray cast=res2.getBody().getObject().getJSONArray("cast");
        setActor(new ArrayList<String>());

        for (int i =0;i<cast.length();i++){
            String member=cast.getJSONObject(i).getString("name");
            addActor(member);
        }


        /**Recupération des extraits**/

        HttpResponse<JsonNode> extract = Unirest.get("https://api.themoviedb.org/3/tv/"+tv_id+"/season/"+season_number+"/episode/"+episode_number+"/videos?api_key="+key).asJson();
        JSONObject res3 =extract.getBody().getObject();
        this.extract=new ArrayList<String>();

        for (int i =0;i<res3.getJSONArray("results").length();i++){
            this.extract.add(res3.getJSONArray("results").getJSONObject(i).getString("key"));
        }

    }


    public static void main(String[] args) {
        try {
            Episode ep=new Episode(78191,1,1);
            System.out.println(ep);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}

