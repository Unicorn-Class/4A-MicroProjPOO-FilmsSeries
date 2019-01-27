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


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static fr.unicornteam.uniflix.UtilAxel.Search.search;


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

    public static ArrayList<Media> research(String str) {
        ArrayList<Media> liste = new ArrayList<>();
        if(searchInBDD(str).size()==0){
            try {
                ArrayList<Integer> ids = search(str);
                System.out.println("Ajout de "+ids.size()+" films...");
                for (int i = 0; i < ids.size(); i++) {
                    Movie m = new Movie(ids.get(i));
                    addMovie(m);
                    liste.add(m);
                    System.out.println("Film "+i+"/"+ids.size()+" ajouté...");
                }
                System.out.println("Returning "+liste.size()+" movies !");
                return liste;
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Trouvé");
            return searchInBDD(str);
        }
        return liste;
    }

    private static void addMovie(Movie m) {
        try {
            //notifFB2(m);
        } catch (Exception e) {
            e.printStackTrace();
        }

        allMovie.add(m);
    }




    private static void notifFB2(Media m) throws Exception {
        String urlParameters = "caption=Nouveau%20film%20dispo%20!%20"+m.getTitle() +
                "&url="+m.getImg() +
                "&access_token=EAAFl9hD5aL4BAM2ZBajLeFd9sMfhnZBstXrpy8xEgZBEhTM1kaqVGxmsXIWZBYU0MVhcOKYeRZCFYZBhsne0IcxMd3IY9xZCxSoAM8pc2wD43P7OKACMjGoGtDJml41rGmbChESZAgo7K4I7ZAb1V4gewelI0ihD1y9pZCsDnEG1xysQZDZD";

        String url = "https://graph.facebook.com/v3.2/me/photos?"+urlParameters;
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
/*
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("caption", "Nouveau%20film%20!%20"+m.getTitle());
        con.setRequestProperty("url",m.getImg());
        con.setRequestProperty("access_token", "EAAFl9hD5aL4BAFLfZCQ1ITHw33fXxTMZCOuEP3BTm8ux7DZCrnz2LZBtXF9oifS2MLuozBPs2gcuqLz9UGwR03UbNIB9zsa5FJaoVR8bsoWt79bH7tHj8ZAldOaz5UVAxNj2QzHCralRhlRtDvbreZAcTFyXQ06eH8ogmKwG3QEXOQYERWyaGrAyun0wFbRmQZD");


*/
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }





    private static void notifFB(Movie m) {

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost2 = new HttpPost("https://graph.facebook.com/v3.2/me/photos" +
                "?caption=Nouveau film dispo ! "+m.getTitle() +
                "&url="+m.img +
                "&access_token=EAAFl9hD5aL4BAFLfZCQ1ITHw33fXxTMZCOuEP3BTm8ux7DZCrnz2LZBtXF9oifS2MLuozBPs2gcuqLz9UGwR03UbNIB9zsa5FJaoVR8bsoWt79bH7tHj8ZAldOaz5UVAxNj2QzHCralRhlRtDvbreZAcTFyXQ06eH8ogmKwG3QEXOQYERWyaGrAyun0wFbRmQZD");




// Request parameters and other properties.
 /*       List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("caption", "Nouveau film dispo !\n"+m.getTitle()));
        params.add(new BasicNameValuePair("url", m.img));
        params.add(new BasicNameValuePair("access_token", "EAAFl9hD5aL4BAFLfZCQ1ITHw33fXxTMZCOuEP3BTm8ux7DZCrnz2LZBtXF9oifS2MLuozBPs2gcuqLz9UGwR03UbNIB9zsa5FJaoVR8bsoWt79bH7tHj8ZAldOaz5UVAxNj2QzHCralRhlRtDvbreZAcTFyXQ06eH8ogmKwG3QEXOQYERWyaGrAyun0wFbRmQZD"));

        System.out.println(params);
  */      try {
         //   httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//Execute and get the response.
            HttpResponse response = httpclient.execute(httppost2);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try (InputStream instream = entity.getContent()) {
                    System.out.println("ENVOYER");
                    System.out.println(instream);
                    System.out.println(httppost2);
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
