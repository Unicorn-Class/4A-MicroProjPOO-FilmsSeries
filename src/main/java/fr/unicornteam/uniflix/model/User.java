package fr.unicornteam.uniflix.model;

import java.util.ArrayList;

public class User extends CommonForUserMedia {

    private String username ="";
    private String email ="";
    private ArrayList<Media> watchList = new ArrayList<>();
    private ArrayList<MediaWatched> mediaWatched = new ArrayList<>();

    public User(String username, ArrayList<MediaWatched> mediaWatched, ArrayList<String> type) {
        this.username = username;
        this.mediaWatched = mediaWatched;
        setType(type);
    }

    public User(String username, ArrayList<MediaWatched> mediaWatched, ArrayList<String> type, ArrayList<Media> watchList) {
        this.username = username;
        this.mediaWatched = mediaWatched;
        setType(type);
        this.watchList = watchList;
    }

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

    public void addWatchList(Media watchList) { this.watchList.add(watchList); }

    public ArrayList<MediaWatched> getMediaWatched() {
        return mediaWatched;
    }

    public void setMediaWatched(ArrayList<MediaWatched> mediaWatched) {
        this.mediaWatched = mediaWatched;
    }

    public void addMediaWatched(MediaWatched mediaWatched) {
        this.mediaWatched.add(mediaWatched);
    }


    public ArrayList<Media> getAllMedia(){
        ArrayList<Media> list = new ArrayList<>();
        list.addAll(watchList);
        for(MediaWatched mw : mediaWatched){
            list.add(mw.getMedia());
        }
        return list;
    }

    public boolean hadInWatched(Media m){
        for(MediaWatched mw : mediaWatched){
            if (mw.getMedia().equals(m)){
                return true;
            }
        }
        return false;
    }

    public boolean hadInWatchList(Media m){
        for(Media mw : watchList){
            if (mw.equals(m)){
                return true;
            }
        }
        return false;
    }

    public int lastView(Media m){
        for(MediaWatched mw : mediaWatched) {
            if (mw.getMedia().equals(m)) {
                return mw.getLastView();
            }
        }
        return 0;

    }


}
