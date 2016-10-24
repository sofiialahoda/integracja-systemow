package pl.pollub.service.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.pollub.service.model.Movie;

public enum EMovieSpecifications {

    HAS_AWARDS(MovieSpecifications.hasAwards());

    public Specification<Movie> specification;

    EMovieSpecifications(Specification<Movie> specification) {
        this.specification = specification;
    }

    public Specification<Movie> getSpecification() {
        return specification;
    }
}
