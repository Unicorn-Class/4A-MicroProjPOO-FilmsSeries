package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.ListForUserMedia;
import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.User;

public class NbCommon {


    //User & Media

    public static final int Category(ListForUserMedia myLFUM, ListForUserMedia m) {
        int common = 0;
        for(String mType : m.getType()){
            for(String myMType : myLFUM.getType()){
                if(mType != null && mType.equals(myMType)){
                    common++;
                }
            }
        }
        return common;
    }

    public static final int Actor(ListForUserMedia myLFUM, ListForUserMedia m) {
        int common = 0;
        for(String mStr : m.getActor()){
            for(String myMStr : myLFUM.getActor()){
                if(mStr != null && mStr.equals(myMStr)){
                    common++;
                }
            }
        }
        return common;
    }

    public static boolean oneCommoCategory(ListForUserMedia myLFUM, ListForUserMedia m) {
        for(String mType : m.getType()){
            for(String myMType : myLFUM.getType()){
                if(mType != null && mType.equals(myMType)){
                    return true;
                }
            }
        }
        return false;
    }


    //User


    public static final int Movie(User myUser, User u) {
        int common = 0;
        for(Media uStr : u.getAllMedia()){
            for(Media myUStr : myUser.getAllMedia()){
                if(uStr != null && uStr.equals(myUStr)){
                    common++;
                }
            }
        }
        return common;
    }


    //Media


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
