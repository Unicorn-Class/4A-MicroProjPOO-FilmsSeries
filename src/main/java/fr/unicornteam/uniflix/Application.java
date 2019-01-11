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


		ArrayList<Media> allMedia = UtilNico.initMovie();
		ArrayList<User> allUser = UtilNico.initUser(allMedia);

		ArrayList<MediaSuggest> filmsLink = MediaSuggestion.getSuggestionMedia(allMedia.get(0), allMedia);

		System.out.println("Films similaires a "+allMedia.get(0).getTitle());
		for(MediaSuggest ms : filmsLink){
			System.out.println(ms);
		}

		System.out.println("\n\n\n");

		ArrayList<UserSuggest> userLink = UserSuggestion.getSuggestionUser(allUser.get(0), allUser);

		System.out.println("Utilisateur similaires a "+allUser.get(0).getUsername());
		for(UserSuggest us : userLink){
			System.out.println("\t"+us);
		}

		userLink = UserSuggestion.getSuggestionUser(allUser.get(1), allUser);

		System.out.println("\nUtilisateur similaires a "+allUser.get(1).getUsername());
		for(UserSuggest us : userLink){
			System.out.println("\t"+us);
		}

		userLink = UserSuggestion.getSuggestionUser(allUser.get(2), allUser);

		System.out.println("\nUtilisateur similaires a "+allUser.get(2).getUsername());
		for(UserSuggest us : userLink){
			System.out.println("\t"+us);
		}

		userLink = UserSuggestion.getSuggestionUser(allUser.get(3), allUser);

		System.out.println("\nUtilisateur similaires a "+allUser.get(3).getUsername());
		for(UserSuggest us : userLink){
			System.out.println("\t"+us);
		}

		userLink = UserSuggestion.getSuggestionUser(allUser.get(4), allUser);

		System.out.println("\nUtilisateur similaires a "+allUser.get(4).getUsername());
		for(UserSuggest us : userLink){
			System.out.println("\t"+us);
		}

	}


	private static ArrayList<String>[] toAL(String[] args) {
		return null;
	}
}
