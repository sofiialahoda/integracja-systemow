package pl.pollub.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.pollub.service.model.Movie;
import pl.pollub.service.model.MovieList;
import pl.pollub.service.repository.EMovieSpecifications;
import pl.pollub.service.repository.MovieRepository;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public Movie movie(@PathVariable("id") String id) {
        return repository.findOne(id);
    }

    @RequestMapping("/movies")
    public MovieList moviesListSelect(@RequestParam(required = false, defaultValue = "ALL") EMovieSpecifications filter,
                                      @RequestParam(required = false) String direction,
                                      @RequestParam(required = false) String order,
                                      @RequestParam(required = false) int page,
                                      @RequestParam(required = false) int size) {

        if (isSortable(direction, order) && isPageable(page, size)) {
            Sort sort = new Sort(direction, order);
            Pageable pageable = new PageRequest(page, size, sort);
            List<Movie> movies = repository.findAll(filter.getSpecification(), pageable).getContent();
            return new MovieList(movies);
        }

        if (isSortable(direction, order)) {
            Sort sort = new Sort(direction, order);
            List<Movie> movies = repository.findAll(filter.getSpecification(), sort);
            return new MovieList(movies);
        }

        if (isPageable(page, size)) {
            Pageable pageable = new PageRequest(page, size);
            List<Movie> movies = repository.findAll(filter.getSpecification(), pageable).getContent();
            return new MovieList(movies);
        }

        return new MovieList(repository.findAll(filter.getSpecification()));
    }

    private boolean isSortable(String direction, String order) {
        return direction != null && order != null;
    }

    private boolean isPageable(int page, int size) {
        return page >= 0 && size > 0;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.DELETE)
    public void moviesListDelete() {
        repository.deleteAll();
    }

    @RequestMapping("/movies/count")
    public long moviesCount() {
        return repository.count();
    }
}