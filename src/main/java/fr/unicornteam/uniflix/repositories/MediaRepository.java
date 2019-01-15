package fr.unicornteam.uniflix.repositories;

import fr.unicornteam.uniflix.model.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<Media, Integer> {
}
