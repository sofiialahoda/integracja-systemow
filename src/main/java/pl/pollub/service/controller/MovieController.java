package pl.pollub.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.pollub.service.model.Movie;
import pl.pollub.service.model.MovieList;
import pl.pollub.service.repository.MovieRepository;

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
    public MovieList moviesListSelect(@RequestParam(required = false) String title,
                                      @RequestParam(required = false) Integer year,
                                      @RequestParam(required = false) String direction,
                                      @RequestParam(required = false) String order,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {

        Sort sort = new Sort(Sort.Direction.fromString(direction), order);
        Pageable pageable = new PageRequest(page, size, sort);
        return new MovieList(repository.findAll(pageable).getContent());
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