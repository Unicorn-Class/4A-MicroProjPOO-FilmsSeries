package fr.unicornteam.uniflix.model;


 public class MediaWatched {
    private Media media;
     private int time;
     private int score;

     public MediaWatched(Media media, int time, int score) {
         this.media = media;
         this.time = time;
         this.score = score;
     }

     public Media getMedia() {
         return media;
     }

     public void setMedia(Media media) {
         this.media = media;
     }

     public int getTime() {
         return time;
     }

     public void setTime(int time) {
         this.time = time;
     }

     public int getScore() {
         return score;
     }

     public void setScore(int score) {
         this.score = score;
     }
 }
