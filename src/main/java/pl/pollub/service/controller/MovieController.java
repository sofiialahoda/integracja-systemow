package pl.pollub.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.service.model.Movie;
import pl.pollub.service.repository.MovieRepository;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping("/movies")
    public Iterable<Movie> moviesList() {
        return repository.findAll();
    }

    @RequestMapping("/movies/destroy")
    public String destroy() {
        repository.deleteAll();
        return "redirect:/movies";
    }

    @RequestMapping("/movies/count")
    public long moviesCount() {
        return repository.count();
    }
}