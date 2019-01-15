package fr.unicornteam.uniflix.repositories;

import fr.unicornteam.uniflix.model.Serie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends CrudRepository<Serie, Integer> {
}
