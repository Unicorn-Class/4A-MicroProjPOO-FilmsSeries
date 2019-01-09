package fr.unicornteam.uniflix;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

public class Serie extends Media {
    int id;
    State currentState;
    ArrayList<Season> seasonList;
    /**Reste à fixer**/
    public Serie(String keyword) throws UnirestException {
        this.seasonList=new ArrayList<Season>();
        String key ="8600861f4787df9fb2f5752da938b459";
        HttpResponse<JsonNode> response = Unirest.get("https://api.themoviedb.org/3/search/tv?page=1&query="+keyword+"&language=fr-fr&api_key="+key).asJson();
        JSONObject obj= new JSONObject(response.getBody());
        this.id=obj.getInt("id");
        this.title=obj.getString("name");
        long date=obj.getLong("first_air_date");
        this.release=new Date(date);
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", currentState=" + currentState +
                ", seasonList=" + seasonList +
                ", title='" + title + '\'' +
                ", release=" + release +
                ", scenarist='" + scenarist + '\'' +
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
        }

    }
}


