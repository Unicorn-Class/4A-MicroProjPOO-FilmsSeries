package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.Media;

//TODO remove public and add sorting method in MediaSuggestion
public class MediaSuggest implements Comparable<MediaSuggest>{
    private Media media;
    private int score = 0;
    public static final int COLLECTION = 999999;
    public static final int UNIVERSE = 999998;

    public MediaSuggest(Media media, int score){
        this.media = media;
        this.score = score;
    }

    public Media getMedia() {
        return media;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return media.getTitle() + " ["+ score +"]";
    }

    @Override
    public int compareTo(MediaSuggest ms) {
        return ms.getScore()-this.score;
    }
}
