package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.MediaWatched;
import fr.unicornteam.uniflix.model.User;

import java.util.*;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public final class MediaSuggestion {

    private static final int COEFF_CATEGORY = 6;
    private static final int COEFF_ACTOR = 6;
    private static final int COEFF_DIRECTOR = 5;
    private static final int COEFF_LANGUAGE = 1;
    private static final int COEFF_SCENARIST = 5;


    private static final int COEFF_WATCHLIST = 3;
    private static final int COEFF_WATCHED = 2;
    private static final int LIMIT_LASTVIEW = 14;

    public static final ArrayList<MediaSuggest> getSuggestionMedia(User myUser, ArrayList<Media> allMedia){

        ArrayList<MediaSuggest> list = new ArrayList<>();

        for(MediaWatched mw : myUser.getMediaWatched()){
            for(MediaSuggest ms : getSuggestionMedia(mw.getMedia(),allMedia)){
                if(!myUser.hadInWatched(ms.getMedia()) || (myUser.hadInWatched(ms.getMedia()) && myUser.lastView(ms.getMedia()) > LIMIT_LASTVIEW)) {
                    list.add(new MediaSuggest(ms.getMedia(), ms.getScore() * COEFF_WATCHED));
                }
            }
        }

        for(Media mwl : myUser.getWatchList()){
            for(MediaSuggest ms : getSuggestionMedia(mwl,allMedia)){
                if(!myUser.hadInWatched(ms.getMedia()) || (myUser.hadInWatched(ms.getMedia()) && myUser.lastView(ms.getMedia()) > LIMIT_LASTVIEW)){
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
