package pl.pollub.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.service.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
}
