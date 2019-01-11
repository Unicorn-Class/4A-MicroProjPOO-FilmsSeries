package fr.unicornteam.uniflix.model.Suggestion;

import fr.unicornteam.uniflix.model.Media;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaSuggest that = (MediaSuggest) o;
        return Objects.equals(media, that.media);
    }

    @Override
    public int hashCode() {
        return Objects.hash(media);
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
