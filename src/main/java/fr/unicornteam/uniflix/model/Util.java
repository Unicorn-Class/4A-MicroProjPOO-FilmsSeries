package fr.unicornteam.uniflix.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

}
