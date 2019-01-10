package fr.unicornteam.uniflix.modelOri;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Article {
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", creation=" + creation +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", auteur='" + auteur + '\'' +
                '}';
    }


    @Id
    private String id;
    private Date creation;
    private String title;
    private String content;
    private String auteur;

    public void setId(String i) {
        id = i;
    }

    public void setCreation(Date d) {
        creation = d;
    }

    public void setTitle(String t) {
        title = t;
    }

    public void setContent(String c) {
        content = c;
    }

    public void setAuteur(String a) {
        auteur = a;
    }

    public String getId() {
        return id;
    }

    public Date getCreation() {
        return creation;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuteur() {
        return auteur;
    }
}
