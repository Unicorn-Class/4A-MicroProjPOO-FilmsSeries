package fr.unicornteam.uniflix.model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Movie extends Media {
    @Id
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Movie> getListeMovie() {
        return listeMovie;
    }

    public void setListeMovie(ArrayList<Movie> listeMovie) {
        this.listeMovie = listeMovie;
    }

    public Movie(int id, String title, ArrayList<String> scenarist, ArrayList<String> actor, ArrayList<String> type, ArrayList<String> director, ArrayList<String> language, ArrayList<Media> universe, ArrayList<Media> collection, double avg, String url) {
        this.id = id;
        this.title = title;
        this.scenarist = scenarist;
        setActor(actor);
        setType(type);
        this.director = director;
        this.language = language;
        this.universe = universe;
        this.collection = collection;
        this.averageScore = avg;
    }
    public Movie(int id, String title, ArrayList<String> scenarist, ArrayList<String> actor, ArrayList<String> type, ArrayList<String> director, ArrayList<String> language, ArrayList<Media> universe, ArrayList<Media> collection, double avg, String url, String video) {
        this.id = id;
        this.title = title;
        this.scenarist = scenarist;
        setActor(actor);
        setType(type);
        this.director = director;
        this.language = language;
        this.universe = universe;
        this.collection = collection;
        this.averageScore = avg;
        this.img = url;
        this.addExtract(video);
    }


    ArrayList<Movie> listeMovie=new ArrayList<Movie>();

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", listeMovie=" + listeMovie +
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
                '}';
    }

    public Movie(String keyMovie) throws ParseException, UnirestException {

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
        this.img="https://image.tmdb.org/t/p/w1280"+res2.getString("poster_path");


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

    public Movie(Integer id) {
        System.out.println("Fetching API Infos for movie : "+id);
        this.id = id;
        String key="8600861f4787df9fb2f5752da938b459";
        /**Get additionnal information**/
        try {
            HttpResponse<JsonNode> additionnal = Unirest.get("https://api.themoviedb.org/3/movie/"+this.id+"?api_key="+key).asJson();
            System.out.println("API Response For Common Infos : "+additionnal.getStatus());
            JSONObject res2 = additionnal.getBody().getObject();
            this.title=res2.optString("original_title");
            String date=res2.optString("release_date","1900-01-01");
            try {
                this.release=new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                System.err.println("Error while parsing date for "+id);
                e.printStackTrace();
                this.release=new Date();
            }
            this.overview=res2.optString("overview");

            ArrayList<String> t=new ArrayList<String>();
            for (int i =0;i<res2.getJSONArray("genres").length();i++){
                t.add(res2.getJSONArray("genres").getJSONObject(i).optString("name"));
            }

            this.setType(t);
            //System.out.println("Ok 1 ?");
            //this.origin_country=res2.getJSONArray("production_countries").getJSONObject(0).optString("name");
            System.out.println("Ok 2 ?");
            this.averageScore=(float)res2.optDouble("vote_average");
            System.out.println("Ok 3 ?");
            if (res2.optString("poster_path", "img/unknown.png").equals("img/unknown.png")) this.img = "img/unknown.png";
            else this.img="https://image.tmdb.org/t/p/w1280"+res2.optString("poster_path");


            HttpResponse<JsonNode> ensemble=Unirest.get("https://api.themoviedb.org/3/movie/"+this.id+"/credits?api_key="+key).asJson();
            System.out.println("API Response For Ensemble : "+ensemble.getStatus());
            JSONObject ens=ensemble.getBody().getObject();

            ArrayList<String> act=new ArrayList<String>();
            for(int i=0;i<ens.getJSONArray("cast").length();i++){
                act.add(ens.getJSONArray("cast").getJSONObject(i).optString("name"));
            }

            this.setActor(act);

            this.scenarist=new ArrayList<String>();
            this.director=new ArrayList<String>();

            for (int i =0;i<ens.getJSONArray("crew").length();i++) {
                String job = ens.getJSONArray("crew").getJSONObject(i).optString("job");
                String member = ens.getJSONArray("crew").getJSONObject(i).optString("name");
                if(job.equalsIgnoreCase("Director")){
                    this.director.add(member);
                }
                else if(job.equalsIgnoreCase("Writer")){
                    this.scenarist.add(member);
                }
            }
            this.distributor=new ArrayList<String>();
            for (int i =0;i<res2.getJSONArray("production_companies").length();i++){
                this.distributor.add(res2.getJSONArray("production_companies").getJSONObject(i).optString("name"));
            }
            /**Recupération des extraits**/
            HttpResponse<JsonNode> extract = Unirest.get("https://api.themoviedb.org/3/movie/" + this.id + "/videos?api_key=" + key).asJson();
            System.out.println("API Response For Trailers : "+extract.getStatus());
            JSONObject res3 = extract.getBody().getObject();
            this.extract = new ArrayList<String>();

            for (int i = 0; i < res3.getJSONArray("results").length(); i++) {
                this.extract.add(res3.getJSONArray("results").getJSONObject(i).optString("key"));
            }
            System.out.println("Fin de l'ajout du film !");
        } catch (UnirestException e) {
            System.err.println("Error while getting result of API request for "+id);
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        try {
            Movie s=new Movie("Moi moche et méchant");
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println(s);
            String sql = "INSERT INTO movie (title, release_date, overview, image, country) VALUES ('"+s.title+"','"+s.release+"','"+s.overview+"','"+s.img+"','"+s.origin_country+"')";
            System.out.println("sql = " + sql);
            stmt.executeUpdate(sql);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
