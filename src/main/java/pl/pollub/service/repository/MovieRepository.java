package pl.pollub.service.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.service.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, String> {
    List<Movie> findAll(Sort sort);
    List<Movie> findAll();
    List<Movie> findAll(Iterable<String> strings);
}
