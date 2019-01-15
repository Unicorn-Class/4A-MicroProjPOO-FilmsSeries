package fr.unicornteam.uniflix.repositories;

import fr.unicornteam.uniflix.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
