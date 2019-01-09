package fr.unicornteam.uniflix;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

public abstract class Media {
    String title;
    Date release;
    String scenarist;
    List<String> actor;
    List<String> type;
    Time duration;
    List<String> director;
    List<String> distributor;
    List<String> extract;
    List<String> language;
    List<Media> universe;
    List<Media> collection;
    List<Media> group;


}
