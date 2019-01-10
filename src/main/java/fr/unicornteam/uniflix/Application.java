package fr.unicornteam.uniflix;

import fr.unicornteam.uniflix.model.Film;
import fr.unicornteam.uniflix.model.Media;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		System.out.println("Hello");

		ArrayList<String> scenarist = new ArrayList<>();
		ArrayList<String> actor = new ArrayList<>();
		ArrayList<String> type = new ArrayList<>();
		ArrayList<String> director = new ArrayList<>();
		ArrayList<String> language = new ArrayList<>();
		ArrayList<Media> universe = new ArrayList<>();
		ArrayList<Media> collection = new ArrayList<>();

		actor.add("Mickael J. Fox");
		type.add("Classic");

		Media film1 = new Film(1, "Retour vers le futur", scenarist, actor, type, director, language, universe, collection);

		System.out.println(film1);
		/*ArrayList<Media> films = new ArrayList<Media>();



		films.add(new Film();

		MediaSuggestion.getSuggestionMedia()*/
	}
}
