package fr.unicornteam.uniflix.UtilAxel;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;

import java.util.ArrayList;

public class Search {

    public static ArrayList<Integer> search(String query) throws UnirestException {
        query=query.replaceAll(" ","%20");
        String key="8600861f4787df9fb2f5752da938b459";
        HttpResponse<JsonNode> response = Unirest.get("https://api.themoviedb.org/3/search/movie?api_key="+key+"&language=en-US&query="+query).asJson();
        JSONArray rs =response.getBody().getObject().getJSONArray("results");
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < rs.length(); i++){
            int id = rs.getJSONObject(i).getInt("id");
            System.out.println("Found a movie in API search : "+id);
            list.add(id);
        }
        return list;
    }

}
