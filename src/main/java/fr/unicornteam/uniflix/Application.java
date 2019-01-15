package fr.unicornteam.uniflix;

import fr.unicornteam.uniflix.UtilNico.UtilNico;
import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.Suggestion.MediaSuggest;
import fr.unicornteam.uniflix.model.Suggestion.MediaSuggestion;
import fr.unicornteam.uniflix.model.Suggestion.UserSuggest;
import fr.unicornteam.uniflix.model.Suggestion.UserSuggestion;
import fr.unicornteam.uniflix.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


		UtilNico.test();


	}


	private static ArrayList<String>[] toAL(String[] args) {
		return null;
	}
}
