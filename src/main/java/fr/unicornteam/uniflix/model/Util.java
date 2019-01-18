package fr.unicornteam.uniflix.model;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class Util {

    public static ArrayList<Movie> allMovie = new ArrayList<>();
    public static ArrayList<Serie> allSerie = new ArrayList<>();

    public static String convertDate(String str){
        try {

            SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            dateFormat.parse(str);
            Date d = new Date();
            dateFormat.format(d);
            return myDateFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Media> allMedia(){
        ArrayList<Media> allMedia = new ArrayList<>();

        allMedia.addAll(allMovie);
        allMedia.addAll(allSerie);
        return allMedia;
    }

    public static ArrayList<Movie> allMovie(){
        return allMovie;
    }

    public static ArrayList<Serie> AllSerie(){
        return allSerie;
    }

    public static ArrayList<Media> research(String str){
        if(searchInBDD(str).size()==0){
            System.out.println("Ajout");
            try {
                ArrayList<Media> liste = new ArrayList<>();
                Movie m = new Movie(str);
                liste.add(m);
                addMovie(m);
                return liste;
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Trouvé");
            return searchInBDD(str);
        }
        return null;
    }

    private static void addMovie(Movie m) {
        notifFB(m);

        allMovie.add(m);
    }

    private static void notifFB(Movie m) {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://graph.facebook.com/v3.2/me/feed?message=mm2%20!%20%3C3&link=www.mm2x.com&=");

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("caption", "Nouveau film dispo !\n"+m.getTitle()));
        params.add(new BasicNameValuePair("url", m.img));
        params.add(new BasicNameValuePair("access_token", "EAAFl9hD5aL4BAFLfZCQ1ITHw33fXxTMZCOuEP3BTm8ux7DZCrnz2LZBtXF9oifS2MLuozBPs2gcuqLz9UGwR03UbNIB9zsa5FJaoVR8bsoWt79bH7tHj8ZAldOaz5UVAxNj2QzHCralRhlRtDvbreZAcTFyXQ06eH8ogmKwG3QEXOQYERWyaGrAyun0wFbRmQZD"));

        System.out.println(params);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try (InputStream instream = entity.getContent()) {
                    System.out.println(instream.read());
                    System.out.println("ENVOYER");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static ArrayList<Media> searchInBDD(String str){
        ArrayList<Media> list = new ArrayList<>();
        for(Media m : allMedia()){
            if(m.getTitle().contains(str)){
                list.add(m);
            }
        }
        return list;
    }
}
