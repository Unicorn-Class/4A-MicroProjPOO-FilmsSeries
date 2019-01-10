package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.Media;

public class NbCommon {


public static final int Category(Media myMedia, Media m) {
        int common = 0;
        for(String mType : m.getType()){
        for(String myMType : myMedia.getType()){
        if(mType != null && mType.equals(myMType)){
        common++;
        }
        }
        }
        return common;
        }

public static final int Actor(Media myMedia, Media m) {
        int common = 0;
        for(String mStr : m.getActor()){
        for(String myMStr : myMedia.getActor()){
        if(mStr != null && mStr.equals(myMStr)){
        common++;
        }
        }
        }
        return common;
        }

public static final int Director(Media myMedia, Media m) {
        int common = 0;
        for(String mStr : m.getDirector()){
        for(String myMStr : myMedia.getDirector()){
        if(mStr != null && mStr.equals(myMStr)){
        common++;
        }
        }
        }
        return common;
        }

    public static final int Language(Media myMedia, Media m) {
        int common = 0;
        for(String mStr : m.getLanguage()){
            for(String myMStr : myMedia.getLanguage()){
                if(mStr != null && mStr.equals(myMStr)){
                    common++;
                }
            }
        }
        return common;
    }

    public static final int Scenarist(Media myMedia, Media m) {
        int common = 0;
        for(String mStr : m.getScenarist()){
            for(String myMStr : myMedia.getScenarist()){
                if(mStr != null && mStr.equals(myMStr)){
                    common++;
                }
            }
        }
        return common;
    }
        }
