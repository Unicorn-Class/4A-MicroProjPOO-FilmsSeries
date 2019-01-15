package fr.unicornteam.uniflix.UtilNico;

import fr.unicornteam.uniflix.model.*;
import fr.unicornteam.uniflix.model.Suggestion.MediaSuggest;
import fr.unicornteam.uniflix.model.Suggestion.MediaSuggestion;
import fr.unicornteam.uniflix.model.Suggestion.UserSuggest;
import fr.unicornteam.uniflix.model.Suggestion.UserSuggestion;
import javafx.scene.input.DataFormat;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UtilNico {

  //  public static ArrayList<Media> allMedia = new ArrayList<>();


    public static int searchMedia(String title, ArrayList<Media> allMedia){
        if(title == null){
            return -1;
        }
        for(int i=0 ; i<allMedia.size() ; i++){
            if(title.equals(allMedia.get(i).getTitle())){
                return i;
            }
        }
    return -1;
    }


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


    public static void test() {
        System.out.println("==================");
        System.out.println(" TEST POUR VICTOR ");
        System.out.println("==================");

        System.out.println(convertDate("Tue Jul 13 00:00:00 CEST 2011"));



        System.out.println("===========");
        System.out.println(" TEST NICO ");
        System.out.println("===========");


        Util.allMedia = InsertNico.initMovie();
        ArrayList<User> allUser = InsertNico.initUser(Util.allMedia());

 //       testSuggestMediaFromMedia(allMedia);
//        testSuggestUser(allUser);
//        testSuggestMediaFromUser(allUser.get(4), allMedia);
        testSuggestHome();


        System.out.println("===============");
        System.out.println(" FIN TEST NICO ");
        System.out.println("===============");

    }

    private static void testSuggestHome() {
        for(int i = 0 ; i<5 ; i++) {
            System.out.println("\tFilms proposé #" + i);
            for (Media m : MediaSuggestion.getSuggestion(5)) {
                System.out.println("\t\t" + m.getTitle());
            }
        }

    }

    private static void testSuggestMediaFromUser(User user, ArrayList<Media> allMedia) {

        ArrayList<MediaSuggest> filmsLink = MediaSuggestion.getSuggestionMedia(user, allMedia);

        System.out.println("\tFilms similaires pour "+user.getUsername());
        for(MediaSuggest ms : filmsLink){
            System.out.println("\t\t"+ms);
        }
        System.out.println("\tFilms en liste pour "+user.getUsername());
        for(MediaSuggest ms : MediaSuggestion.getSuggestionMediaFromList(user)){
            System.out.println("\t\t"+ms);
        }
        System.out.println("\t[CLASSIC VERSION] Films en lien avec "+allMedia.get(5));
        for(Media m : allMedia.get(5).getSuggestionMedia(5)){
            System.out.println("\t\t"+m.getTitle());
        }
        System.out.println("\t[NEW VERSION] Films en lien avec "+allMedia.get(5));
        for(Media m : allMedia.get(5).getSuggestionMedia(5)){
            System.out.println("\t\t"+m.getTitle());
        }

    }

    private static void testSuggestMediaFromMedia(ArrayList<Media> allMedia) {

        ArrayList<MediaSuggest> filmsLink = MediaSuggestion.getSuggestionMedia(allMedia.get(0), allMedia);

        System.out.println("\tFilms similaires a "+allMedia.get(0).getTitle());
        for(MediaSuggest ms : filmsLink){
            System.out.println("\t\t"+ms);
        }

    }

    private static void testSuggestUser(ArrayList<User> allUser) {


        ArrayList<UserSuggest> userLink;

        for(int i=0 ; i<allUser.size();i++){

            userLink = UserSuggestion.getSuggestionUser(allUser.get(i), allUser);

            System.out.println("\n\tUtilisateur similaires a "+allUser.get(i).getUsername());
            for(UserSuggest us : userLink){
                System.out.println("\t\t"+us);
            }
        }


    }


}
