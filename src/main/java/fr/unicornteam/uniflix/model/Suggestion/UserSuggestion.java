package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.MediaWatched;
import fr.unicornteam.uniflix.model.User;

import java.util.ArrayList;
import java.util.Collections;

public class UserSuggestion {

    private static final int COEFF_CATEGORY = 6;
    private static final int COEFF_ACTOR = 6;
    private static final int COEFF_MOVIE = 3;
    private static final int COEFF_TIME = 4;
    private static final int COEFF_SEEnSCORE = 8;
    private static final int COEFF_WATCHLIST = 5;
    private static final int LIMIT_MOVIE = 0;
    private static final int LIMIT_TIME = 2;


    //
    //TODO
    //
    //  TODO    end calcul score
    //  TODO    criteria
    //
    //TODO
    //


    public static final ArrayList<UserSuggest> getSuggestionUser(User myUser, ArrayList<User> allUser){

        ArrayList<UserSuggest> listUser = new ArrayList<>();

        for(User u : allUser){
            if(criteria(myUser, u) && myUser!=u){
                listUser.add(new UserSuggest(u, calculScore(myUser, u)));
            }
        }

        Collections.sort(listUser);
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

        if(nbMedia != 0) {
            score += (scoreMedia / nbMedia) * COEFF_WATCHLIST;
        }


        scoreMedia = 0;
        nbMedia = 0;

            for(MediaWatched myUMW : myUser.getMediaWatched()){
                for(MediaWatched uMW : u.getMediaWatched()){
                    nbMedia++;
                    if(myUMW.getScore()>uMW.getScore()-1){
                        scoreMedia += uMW.getScore();
                        if(myUMW.getTime()>LIMIT_TIME && uMW.getTime()>LIMIT_TIME){
                            scoreMedia += min(myUMW.getTime(), uMW.getTime()) * COEFF_TIME;
                        }
                    }
            }
        }

        if(nbMedia != 0) {
            score += (scoreMedia / nbMedia) * COEFF_SEEnSCORE;
        }

        score += NbCommon.Movie(myUser, u) * COEFF_MOVIE;
        return score;
    }

    private static int min(int time, int time1) {
        if(time>time1){
            return time;
        }
        return time1;
    }

    private static boolean criteria(User myUser, User u) {
        return NbCommon.oneCommoCategory(myUser, u) || NbCommon.Movie(myUser, u) > LIMIT_MOVIE;
    }

}
