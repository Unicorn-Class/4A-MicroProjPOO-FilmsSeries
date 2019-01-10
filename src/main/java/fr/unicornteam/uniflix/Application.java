package fr.unicornteam.uniflix;

import fr.unicornteam.uniflix.model.Film;
import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.Suggestion.MediaSuggest;
import fr.unicornteam.uniflix.model.Suggestion.MediaSuggestion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		System.out.println("Hello");


		ArrayList<Media> allMedia = initBDDTestNico();

		ArrayList<MediaSuggest> filmsLink = MediaSuggestion.getSuggestionMedia(allMedia.get(0), allMedia);

		System.out.println("Films similaires a "+allMedia.get(0));
		for(MediaSuggest ms : filmsLink){
			System.out.println(ms);
		}
		/*ArrayList<Media> films = new ArrayList<Media>();



		films.add(new Film();

		MediaSuggestion.getSuggestionMedia()*/
	}

	private static ArrayList<Media> initBDDTestNico() {

		ArrayList<Media> allMedia = new ArrayList<>();

		//		id	titre	scenarist	actor	type	director	language	universe	collection

		Media film1 = new Film(1, "Retour vers le futur",
				new ArrayList<String>() {{add("Robert Zemeckis");add("Bob Gale");}},
				new ArrayList<String>() {{add("Michael J. Fox");add("Christopher Lloyd");add("Lea Thompson");add("Crispin Glover");add("Thomas F. Wilson");}},
				new ArrayList<String>() {{add("SF");add("Classic");}},
				new ArrayList<String>() {{add("Robert Zemeckis");add("Steven Spielberg");}},
				new ArrayList<String>() {{add("fr");add("en");add("es");}},
				new ArrayList<>(),
				new ArrayList<>());
		Media film2 = new Film(2, "Retour vers le futur II",
				new ArrayList<String>() {{add("Robert Zemeckis");add("Bob Gale");}},
				new ArrayList<String>() {{add("Michael J. Fox");add("Christopher Lloyd");add("Thomas F. Wilson");add("Lea Thompson");}},
				new ArrayList<String>() {{add("SF");add("Classic");}},
				new ArrayList<String>() {{add("Robert Zemeckis");add("Steven Spielberg");}},
				new ArrayList<String>() {{add("fr");add("en");add("es");}},
				new ArrayList<>(),
				new ArrayList<Media>() {{add(film1);}});
		Media film3 = new Film(3, "Retour vers le futur III",
				new ArrayList<String>() {{add("Bob Gale");}},
				new ArrayList<String>() {{add("Elisabeth Shue");add("Michael J. Fox");add("Christopher Lloyd");add("Mary Steenburgen");add("Thomas F. Wilson");}},
				new ArrayList<String>() {{add("SF");add("Classic");}},
				new ArrayList<String>() {{add("Robert Zemeckis");add("Steven Spielberg");}},
				new ArrayList<String>() {{add("fr");add("en");}},
				new ArrayList<>(),
				new ArrayList<Media>() {{add(film1);add(film2);}});
		Media film4 = new Film(4, "Harry Potter : l'ecole des sorciers",
				new ArrayList<String>() {{add("Steven Kloves");}},
				new ArrayList<String>() {{add("Daniel Radcliffe");add("Rupert Grint");add("Emma Watson");add("Robbie Coltrane");add("Richard Harris");add("Ian Hart");add("Maggie Smith");add("Alan Rickman");}},
				new ArrayList<String>() {{add("SF");}},
				new ArrayList<String>() {{add("Chris Columbus");}},
				new ArrayList<String>() {{add("fr");add("en");}},
				new ArrayList<>(),
				new ArrayList<>());
		Media film5 = new Film(5, "Wallace et Gromit : Le Mystère du lapin-garou",
				new ArrayList<String>() {{add("Nick Park");add("Steve Box");}},
				new ArrayList<String>() {{add("Peter Sallis");}},
				new ArrayList<String>() {{add("Classic");add("Anime");}},
				new ArrayList<String>() {{add("Nick Park");add("Steve Box");}},
				new ArrayList<String>() {{add("en");}},
				new ArrayList<>(),
				new ArrayList<>());

		allMedia.add(film1);
		allMedia.add(film2);
		allMedia.add(film3);
		allMedia.add(film4);
		allMedia.add(film5);

		return allMedia;
	}

	private static ArrayList<String>[] toAL(String[] args) {
		return null;
	}
}
