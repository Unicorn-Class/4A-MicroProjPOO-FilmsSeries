package fr.unicornteam.uniflix.UtilNico;

import fr.unicornteam.uniflix.model.Film;
import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.MediaWatched;
import fr.unicornteam.uniflix.model.User;

import java.util.ArrayList;

public class InsertNico {



    public static ArrayList<Media> initMovie() {

        ArrayList<Media> allMedia = new ArrayList<>();

        //		id	titre	scenarist	actor	type	director	language	universe	collection

        Media film1 = new Film(1, "Retour vers le futur",
                new ArrayList<String>() {{add("Robert Zemeckis");add("Bob Gale");}},
                new ArrayList<String>() {{add("Michael J. Fox");add("Christopher Lloyd");add("Lea Thompson");add("Crispin Glover");add("Thomas F. Wilson");}},
                new ArrayList<String>() {{add("SF");add("Classic");}},
                new ArrayList<String>() {{add("Robert Zemeckis");add("Steven Spielberg");}},
                new ArrayList<String>() {{add("fr");add("en");add("es");}},
                new ArrayList<>(),
                new ArrayList<>(),
                4.7);
        Media film2 = new Film(2, "Retour vers le futur II",
                new ArrayList<String>() {{add("Robert Zemeckis");add("Bob Gale");}},
                new ArrayList<String>() {{add("Michael J. Fox");add("Christopher Lloyd");add("Thomas F. Wilson");add("Lea Thompson");}},
                new ArrayList<String>() {{add("SF");add("Classic");}},
                new ArrayList<String>() {{add("Robert Zemeckis");add("Steven Spielberg");}},
                new ArrayList<String>() {{add("fr");add("en");add("es");}},
                new ArrayList<>(),
                new ArrayList<Media>() {{add(film1);}},
                4.4);
        film1.addCollection(film2);
        Media film3 = new Film(3, "Retour vers le futur III",
                new ArrayList<String>() {{add("Bob Gale");}},
                new ArrayList<String>() {{add("Elisabeth Shue");add("Michael J. Fox");add("Christopher Lloyd");add("Mary Steenburgen");add("Thomas F. Wilson");}},
                new ArrayList<String>() {{add("SF");add("Classic");}},
                new ArrayList<String>() {{add("Robert Zemeckis");add("Steven Spielberg");}},
                new ArrayList<String>() {{add("fr");add("en");}},
                new ArrayList<>(),
                new ArrayList<Media>() {{add(film1);add(film2);}},
                4.1);
        film1.addCollection(film3);
        film2.addCollection(film3);
        Media film4 = new Film(4, "Harry Potter : l'ecole des sorciers",
                new ArrayList<String>() {{add("Steven Kloves");}},
                new ArrayList<String>() {{add("Daniel Radcliffe");add("Rupert Grint");add("Emma Watson");add("Robbie Coltrane");add("Richard Harris");add("Ian Hart");add("Maggie Smith");add("Alan Rickman");}},
                new ArrayList<String>() {{add("SF");add("Aventure");}},
                new ArrayList<String>() {{add("Chris Columbus");}},
                new ArrayList<String>() {{add("fr");add("en");}},
                new ArrayList<>(),
                new ArrayList<>(),
                2.7);
        Media film5 = new Film(5, "Wallace et Gromit : Le Mystère du lapin-garou",
                new ArrayList<String>() {{add("Nick Park");add("Steve Box");}},
                new ArrayList<String>() {{add("Peter Sallis");}},
                new ArrayList<String>() {{add("Classic");add("Anime");add("Comedie");}},
                new ArrayList<String>() {{add("Nick Park");add("Steve Box");}},
                new ArrayList<String>() {{add("en");}},
                new ArrayList<>(),
                new ArrayList<>(),
                3.8);

        Media film6 = new Film(6, "Deadpool",
                new ArrayList<String>() {{add("Rhett Reese");add("Paul Wernick");}},
                new ArrayList<String>() {{add("Ryan Reynolds");add("Morena Baccarin");add("Ed Skrein");add("T. J. Miller");add("Gina Carano");}},
                new ArrayList<String>() {{add("Classic");add("SH");add("SF");add("Action");}},
                new ArrayList<String>() {{add("Tim Miller");}},
                new ArrayList<String>() {{add("en");add("fr");}},
                new ArrayList<>(),
                new ArrayList<>(),
                5);
        Media film7 = new Film(7, "Sherlock Holmes",
                new ArrayList<String>() {{add("Guy Ritchie");add("Michael Robert Johnson");}},
                new ArrayList<String>() {{add("Robert Downey Jr.");add("Jude Law");add("Rachel McAdams");add("Mark Strong");add("Kelly Reilly");}},
                new ArrayList<String>() {{add("Aventure");add("Action");}},
                new ArrayList<String>() {{add("Guy Ritchie");}},
                new ArrayList<String>() {{add("en");add("fr");}},
                new ArrayList<>(),
                new ArrayList<>(),
                3.1);
        Media film8 = new Film(8, "High School Musical",
                new ArrayList<String>() {{add("Peter Barsocchini");}},
                new ArrayList<String>() {{add("Zac Efron");add("Vanessa Hudgens");
                    add("Ashley Tisdale");add("Lucas Grabeel");add("Monique Coleman");}},
                new ArrayList<String>() {{add("Comedie");add("Musical");}},
                new ArrayList<String>() {{add("Kenny Ortega");}},
                new ArrayList<String>() {{add("en");add("fr");}},
                new ArrayList<>(),
                new ArrayList<>(),
                2.1);
        Media film9 = new Film(9, "Hunger Games",
                new ArrayList<String>() {{add("Gary Ross");add("Suzanne Collins");add("Billy Ray");}},
                new ArrayList<String>() {{add("Jennifer Lawrence");add("Josh Hutcherson");
                    add("Liam Hemsworth");add("Woody Harrelson");add("Stanley Tucci");}},
                new ArrayList<String>() {{add("SF");add("Aventure");add("Action");}},
                new ArrayList<String>() {{add("Gary Ross");}},
                new ArrayList<String>() {{add("en");add("fr");}},
                new ArrayList<>(),
                new ArrayList<>(),
                2.8);
        Media film10 = new Film(10, "Bohemian Rhapsody",
                new ArrayList<String>() {{add("Anthony McCarten");}},
                new ArrayList<String>() {{add("Rami Malek");add("Ben Hardy");
                    add("Joseph Mazzello");add("Gwilym Lee");}},
                new ArrayList<String>() {{add("Historique");add("Musical");}},
                new ArrayList<String>() {{add("Bryan Singer");}},
                new ArrayList<String>() {{add("en");add("fr");}},
                new ArrayList<>(),
                new ArrayList<>(),
                3.4);
        Media film11 = new Film(11, "Black Panter",
                new ArrayList<String>() {{add("Joe Robert Cole");add("Ryan Coogler");}},
                new ArrayList<String>() {{add("Chadwick Boseman");add("Lupita Nyong'o");
                    add("Danai Gurira");add("Martin Freeman");add("Michael B. Jordan");}},
                new ArrayList<String>() {{add("SH");add("SF");add("Aventure");add("Action");}},
                new ArrayList<String>() {{add("Ryan Coogler");}},
                new ArrayList<String>() {{add("en");add("fr");}},
                new ArrayList<>(),
                new ArrayList<>(),
                2.1);

        //		id	titre	scenarist	actor	type	director	language	universe	collection

        Media film12 = new Film(12, "Pirates des Caraïbes : La Malédiction du Black Pearl",
                new ArrayList<String>() {{add("Terry Rossio");add(" \tTed Elliott");}},
                new ArrayList<String>() {{add("Johnny Depp");add("Orlando Bloom");
                    add("Keira Knightley");add("Geoffrey Rush");}},
                new ArrayList<String>() {{add("SF");add("Aventure");add("Action");}},
                new ArrayList<String>() {{add("Gore Verbinski");}},
                new ArrayList<String>() {{add("en");add("fr");}},
                new ArrayList<>(),
                new ArrayList<>(),
                2.0);

        allMedia.add(film1);
        allMedia.add(film2);
        allMedia.add(film3);
        allMedia.add(film4);
        allMedia.add(film5);
        allMedia.add(film6);
        allMedia.add(film7);
        allMedia.add(film8);
        allMedia.add(film9);
        allMedia.add(film10);
        allMedia.add(film11);
        allMedia.add(film12);

        return allMedia;
    }

    public static ArrayList<User> initUser(ArrayList<Media> allMedia) {
        ArrayList<User> allUser = new ArrayList<>();

        User u1 = new User("Elodie",
                new ArrayList<MediaWatched>() {{add(new MediaWatched(allMedia.get(5),
                        new ArrayList<Integer>() {{add(20);add(10);add(2);}},
                        5));add(new MediaWatched(allMedia.get(6),
                        new ArrayList<Integer>() {{add(40);add(30);add(20);add(10);}},
                        4));}},
                new ArrayList<String>() {{add("Comedie");add("Action");}});

        User u2 = new User("Axel",
                new ArrayList<MediaWatched>() {{add(new MediaWatched(allMedia.get(7),
                        new ArrayList<Integer>() {{add(40);add(30);add(20);add(10);}}
                        ,4));add(new MediaWatched(allMedia.get(8),
                        new ArrayList<Integer>() {{add(40);add(30);add(20);add(10);}},
                        5));}},
                new ArrayList<String>() {{add("Comedie");add("SF");}});

        User u3 = new User("Senam",
                new ArrayList<MediaWatched>() {{add(new MediaWatched(allMedia.get(9),
                        new ArrayList<Integer>() {{add(40);add(30);add(20);add(10);}},
                        5));add(new MediaWatched(allMedia.get(10),
                        new ArrayList<Integer>() {{add(40);add(10);}},
                        5));}},
                new ArrayList<String>() {{add("Comedie");add("SF");}});

        User u4 = new User("Victor",
                new ArrayList<MediaWatched>() {{add(new MediaWatched(allMedia.get(11),
                        new ArrayList<Integer>() {{add(40);}},
                        2));add(new MediaWatched(allMedia.get(10),
                        new ArrayList<Integer>() {{add(30);}}
                        ,3));}},
                new ArrayList<String>() {{add("Action");add("Aventure");}});

        User u5 = new User("Nicolas",
                new ArrayList<MediaWatched>() {{add(new MediaWatched(allMedia.get(0),
                        new ArrayList<Integer>() {{add(40);add(10);}},
                        5));add(new MediaWatched(allMedia.get(5),
                        new ArrayList<Integer>() {{add(140);add(130);add(210);add(110);}}
                        ,5));add(new MediaWatched(allMedia.get(11),
                        new ArrayList<Integer>() {{add(40);}}
                        ,1));}},
                new ArrayList<String>() {{add("Classic");add("Aventure");}},
                new ArrayList<Media>(){{add(allMedia.get(1));add(allMedia.get(2));}});

        allUser.add(u1);
        allUser.add(u2);
        allUser.add(u3);
        allUser.add(u4);
        allUser.add(u5);

        return allUser;
    }


}
