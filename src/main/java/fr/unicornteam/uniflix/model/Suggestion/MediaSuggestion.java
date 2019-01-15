package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.UtilNico.UtilNico;
import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.MediaWatched;
import fr.unicornteam.uniflix.model.User;
import fr.unicornteam.uniflix.model.Util;

import java.util.*;

public final class MediaSuggestion {

    private static final int COEFF_CATEGORY = 6;
    private static final int COEFF_ACTOR = 6;
    private static final int COEFF_DIRECTOR = 5;
    private static final int COEFF_LANGUAGE = 1;
    private static final int COEFF_SCENARIST = 5;

    private static final int COEFF_WATCHLIST = 3;
    private static final int COEFF_WATCHED = 2;
    private static final int LIMIT_LASTVIEW = 8;

    private static final int COEFF_SCORE = 2;
    private static final int COEFF_LASTVIEW = 5;
    private static final int COEFF_NBVIEW = 3;
    private static final int COEFF_AVGSCORE = 1;

    private static final double COEFF_RANDOM = 3;

    //TODO remove when avgScore un Media will removed ?
    private static final double LIMIT_SCORE = 2;




    public static final ArrayList<Integer> getIdSuggestion(int nb){
        ArrayList<Integer> listId = new ArrayList<>();
        for(Media m : getSuggestion(nb)){
            listId.add(m.getId());
        }
        return listId;
    }


    public static final ArrayList<Media> getSuggestion(int nb){
        ArrayList<Media> list = new ArrayList<>();
        ArrayList<MediaSuggest> listSugg = new ArrayList<>();

        //Why not add released date
        for(Media m : Util.allMedia()){
            if(m.getAverageScore()>LIMIT_SCORE){
                listSugg.add(new MediaSuggest(m, calculScoreWithRandom(m)));
            }
        }
        Collections.sort(listSugg);

        int limit = nb;
        if(nb>listSugg.size()){
            limit = listSugg.size();
        }

        for(int i=0 ; i<limit ; i++) {
            list.add(listSugg.get(i).getMedia());
        }
        return  list;

    }

    private static int calculScoreWithRandom(Media m) {
        int score = 0;
        score += m.getAverageScore() * COEFF_AVGSCORE;
        score += Math.random()*(COEFF_AVGSCORE*COEFF_RANDOM);
        return score;
    }


    public static final ArrayList<MediaSuggest> getSuggestionMedia(User myUser, ArrayList<Media> allMedia){

        ArrayList<MediaSuggest> list = new ArrayList<>();

        for(MediaWatched mw : myUser.getMediaWatched()){
            for(MediaSuggest ms : getSuggestionMedia(mw.getMedia(),allMedia)){
                if(!myUser.hadInWatchList(ms.getMedia()) && !myUser.hadInWatched(ms.getMedia()) ) {
                    list.add(new MediaSuggest(ms.getMedia(), ms.getScore() * COEFF_WATCHED));
                }
            }
        }

        for(Media mwl : myUser.getWatchList()){
            for(MediaSuggest ms : getSuggestionMedia(mwl,allMedia)){
                if(!myUser.hadInWatchList(ms.getMedia()) && !myUser.hadInWatched(ms.getMedia()) ){
                    list.add(new MediaSuggest(ms.getMedia(), ms.getScore()*COEFF_WATCHLIST));
                }
            }
        }


        Set<MediaSuggest> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);


        Collections.sort(list);
        return list;

    }

    public static final ArrayList<MediaSuggest> getSuggestionMediaFromList(User myUser){

        ArrayList<MediaSuggest> list = new ArrayList<>();

        for(MediaWatched mw: myUser.getMediaWatched()){
            if(myUser.lastView(mw.getMedia()) > LIMIT_LASTVIEW) {
                list.add(new MediaSuggest(mw.getMedia(), calculScore(mw)));
            }
        }


        ArrayList<MediaSuggest> listWL = getSuggestionMedia(myUser, myUser.getWatchList());

        for(MediaSuggest ms : listWL){
            list.add(ms);
        }

        Set<MediaSuggest> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);


        Collections.sort(list);
        return list;

    }


    public static final ArrayList<MediaSuggest> getSuggestionMedia(Media myMedia, ArrayList<Media> allMedia){

        ArrayList<MediaSuggest> listMedia = new ArrayList<MediaSuggest>();

        for(Media m : allMedia){
            if(criteria(myMedia, m) && myMedia!=m){
                listMedia.add(new MediaSuggest(m, calculScore(myMedia, m)));
            }
        }

        Collections.sort(listMedia);

        return listMedia;
    }

    private static int calculScore(Media myMedia, Media m) {
        int score = 0;
        //TODO add calculation of release date
        //TODO add calculation of average score
        score += NbCommon.Category(myMedia, m)*COEFF_CATEGORY;
        score += NbCommon.Actor(myMedia, m)*COEFF_ACTOR;
        score += NbCommon.Director(myMedia, m)*COEFF_DIRECTOR;
        score += NbCommon.Language(myMedia, m)*COEFF_LANGUAGE;
        score += NbCommon.Scenarist(myMedia, m)*COEFF_SCENARIST;
        return score;
    }

    private static int calculScore(MediaWatched mw) {
        int score = 0;
        score += mw.getScore()*COEFF_SCORE;
        score += mw.getLastView()*COEFF_LASTVIEW;
        score -= mw.getNbView()*COEFF_NBVIEW;
        score += mw.getMedia().getAverageScore()*COEFF_AVGSCORE;

        return score;
    }


    //TODO delete ?
    private static int calculScore(Media mwl) {
        int score = 0;
        score += mwl.getAverageScore();
        return score;
    }

    private static ArrayList<MediaSuggest> initListMedia(Media myMedia) {

        ArrayList<MediaSuggest> listMedia = new ArrayList<MediaSuggest>();
        for(Media m : myMedia.getCollection()){
            listMedia.add(new MediaSuggest(m, MediaSuggest.COLLECTION));
        }
        for(Media m : myMedia.getUniverse()){
            listMedia.add(new MediaSuggest(m, MediaSuggest.UNIVERSE));
        }
        return listMedia;
    }

    private static boolean criteria(Media myMedia, Media m) {
        //TODO add criteria release date
        return (m.getAverageScore()>myMedia.getAverageScore()-1)
                && (NbCommon.oneCommoCategory(myMedia, m)
                && !myMedia.hadInUniverse(m)
                && !myMedia.hadInCollection(m));
    }

}
