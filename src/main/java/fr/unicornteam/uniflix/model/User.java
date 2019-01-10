package fr.unicornteam.uniflix.model;

import java.util.ArrayList;

public class User extends ListForUserMedia {

    private String username ="";
    private String email ="";
    private ArrayList<Media> watchList = new ArrayList<>();
    private ArrayList<MediaWatched> mediaWatched = new ArrayList<>();



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Media> getWatchList() {
        return watchList;
    }

    public void setWatchList(ArrayList<Media> watchList) {
        this.watchList = watchList;
    }

    public ArrayList<MediaWatched> getMediaWatched() {
        return mediaWatched;
    }

    public void setMediaWatched(ArrayList<MediaWatched> mediaWatched) {
        this.mediaWatched = mediaWatched;
    }


    public ArrayList<Media> getAllMedia(){
        ArrayList<Media> list = new ArrayList<>();
        list.addAll(watchList);
        for(MediaWatched mw : mediaWatched){
            list.add(mw.getMedia());
        }
        return list;
    }


}
