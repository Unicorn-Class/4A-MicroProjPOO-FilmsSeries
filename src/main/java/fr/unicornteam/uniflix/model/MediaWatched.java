package fr.unicornteam.uniflix.model;


 class MediaWatched {
    private Media media;
    private int time;

     public MediaWatched(Media media, int time) {
         this.media = media;
         this.time = time;
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
 }
