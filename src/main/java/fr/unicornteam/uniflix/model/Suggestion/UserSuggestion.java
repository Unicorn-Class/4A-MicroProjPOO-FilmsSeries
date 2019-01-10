package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.User;

import java.util.ArrayList;

public class UserSuggestion {

    private static final int COEFF_CATEGORY = 6;
    private static final int COEFF_ACTOR = 6;
    private static final int COEFF_MOVIE = 5;


    //
    //TODO
    //
    //  TODO    end calcul score
    //  TODO    criteria
    //
    //TODO
    //


    public static final ArrayList<UserSuggest> getSuggestionMedia(User myUser, ArrayList<User> allUser){

        ArrayList<UserSuggest> listUser = new ArrayList<>();

        for(User u : allUser){
            if(criteria(myUser, u) && myUser!=u){
                listUser.add(new UserSuggest(u, calculScore(myUser, u)));
            }
        }

        return listUser;
    }

    private static int calculScore(User myUser, User u) {
        int score = 0;
        //TODO add calculation of release date
        //TODO add calculation of average score
        score += NbCommon.Category(myUser, u)*COEFF_CATEGORY;
        score += NbCommon.Actor(myUser, u)*COEFF_ACTOR;

        int scoreMedia = 0;
        int nbMedia = 0;

        for(Media m : myUser.getWatchList()){
            for(MediaSuggest ms : MediaSuggestion.getSuggestionMedia(m, myUser.getAllMedia())){
                nbMedia++;
                scoreMedia += ms.getScore();
            }
        }

        score += (scoreMedia/nbMedia)*COEFF_MOVIE;
/*
        score += NbCommon.Director(myMedia, m)*COEFF_DIRECTOR;
        score += NbCommon.Language(myMedia, m)*COEFF_LANGUAGE;
        score += NbCommon.Scenarist(myMedia, m)*COEFF_SCENARIST;*/
        return score;
    }

    private static boolean criteria(User myUser, User u) {
       // return oneCommoCategory(myMedia, m);
       return true;
    }

}
