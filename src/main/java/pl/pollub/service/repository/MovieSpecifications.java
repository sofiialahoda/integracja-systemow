package pl.pollub.service.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.pollub.service.model.Movie;

class MovieSpecifications {
    static Specification<Movie> hasAwards() {
        return (root, query, builder) -> builder.notEqual(root.get("awards"), "N/A");
    }
}
