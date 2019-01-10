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

public class Season extends Media {
    int id;
    ArrayList<Episode> episodeList;

    public Season(int tv_id, int season_number) throws UnirestException,ParseException {
        /**Request to the API**/
        String key ="8600861f4787df9fb2f5752da938b459";
        HttpResponse<JsonNode> response = Unirest.get("https://api.themoviedb.org/3/tv/"+tv_id+"/season/"+season_number+"?api_key="+key).asJson();
        JSONObject res =response.getBody().getObject();
        this.id=res.getInt("id");
        String date=res.getString("air_date");
        this.release=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.title=res.getString("name");
        this.overview=res.getString("overview");
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release=" + release +
                ", overview='" + overview + '\'' +
                '}';
    }

    public static void main(String[] args) {
        try {
            Season s=new Season(78191,1);
            System.out.println(s);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
