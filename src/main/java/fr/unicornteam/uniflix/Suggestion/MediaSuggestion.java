package fr.unicornteam.uniflix.Suggestion;

import fr.unicornteam.uniflix.Media;

import java.util.ArrayList;

public final class MediaSuggestion {

    private final int COEFF_CATEGORY = 6;
    private final int COEFF_ACTOR = 6;
    private final int COEFF_DIRECTOR = 5;
    private final int COEFF_LANGUAGE = 1;
    private final int COEFF_SCENARIST = 5;

    public ArrayList<Media> getSuggestionMedia(Media myMedia, ArrayList<Media> allMedia){

        ArrayList<MediaSuggest> listMedia = initListMedia(myMedia);

        for(Media m : allMedia){
            if(criteria(myMedia, m)){
                listMedia.add(new MediaSuggest(m, calculScore(myMedia, m)));
            }
        }

        return null;
    }

    private int calculScore(Media myMedia, Media m) {
        int score = 0;
        //TODO add calculation of release date
        score += NbCommon.Category(myMedia, m)*COEFF_CATEGORY;
        score += NbCommon.Actor(myMedia, m)*COEFF_ACTOR;
        score += NbCommon.Director(myMedia, m)*COEFF_DIRECTOR;
        score += NbCommon.Language(myMedia, m)*COEFF_LANGUAGE;
        score += NbCommon.Scenarist(myMedia, m)*COEFF_SCENARIST;
        return score;
    }


    private ArrayList<MediaSuggest> initListMedia(Media myMedia) {

        ArrayList<MediaSuggest> listMedia = new ArrayList<MediaSuggest>();
        for(Media m : myMedia.getCollection()){
            listMedia.add(new MediaSuggest(m, MediaSuggest.COLLECTION));
        }
        for(Media m : myMedia.getUniverse()){
            listMedia.add(new MediaSuggest(m, MediaSuggest.UNIVERSE));
        }
        return listMedia;
    }

    private boolean criteria(Media myMedia, Media m) {
        //TODO add criteria release date
        return (m.getAverageScore()>myMedia.getAverageScore()-1)
                && (oneCommoCategory(myMedia, m));
    }

    private boolean oneCommoCategory(Media myMedia, Media m) {
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
