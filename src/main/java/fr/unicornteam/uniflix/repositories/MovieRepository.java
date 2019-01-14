package fr.unicornteam.uniflix.repositories;

import fr.unicornteam.uniflix.model.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Film, Integer> {

}
