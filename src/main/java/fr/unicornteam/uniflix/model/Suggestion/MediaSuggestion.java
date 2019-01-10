package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.Media;

import java.util.ArrayList;

public final class MediaSuggestion {

    private static final int COEFF_CATEGORY = 6;
    private static final int COEFF_ACTOR = 6;
    private static final int COEFF_DIRECTOR = 5;
    private static final int COEFF_LANGUAGE = 1;
    private static final int COEFF_SCENARIST = 5;

    public static final ArrayList<MediaSuggest> getSuggestionMedia(Media myMedia, ArrayList<Media> allMedia){

        ArrayList<MediaSuggest> listMedia = initListMedia(myMedia);

        for(Media m : allMedia){
            if(criteria(myMedia, m) && myMedia!=m){
                listMedia.add(new MediaSuggest(m, calculScore(myMedia, m)));
            }
        }

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
                && (oneCommoCategory(myMedia, m));
    }

    private static boolean oneCommoCategory(Media myMedia, Media m) {
        for(String mType : m.getType()){
            for(String myMType : myMedia.getType()){
                if(mType != null && mType.equals(myMType)){
                    return true;
                }
            }
        }
        return false;
    }

}
