package pl.pollub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pollub.service.model.Movie;
import pl.pollub.service.repository.MovieRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

@Component
public class ImdbFetcher {

    @Autowired
    private MovieRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(ImdbFetcher.class);

    private final String[] ids = {
            "1527186",
            "1527835",
            "1528071",
            "1528100",
            "1528224",
            "1528750",
            "1528854",
            "1529307",
            "1529572",
            "1530509",
            "1530975",
            "1530983",
            "1531663",
            "1531901",
            "1531924",
            "1531930"
    };


    public void destroy() {
        repository.deleteAll();
    }

    public String fetch(String imdbId) throws Exception {
        String url = "http://www.omdbapi.com/?i=tt" + imdbId + "&plot=full&r=json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void push() throws Exception {
        Arrays.stream(ids).forEach(id -> {
            try {
                String json = fetch(id);
                Movie movie = mapper.readValue(json, Movie.class);
                repository.save(movie);
            } catch (Exception e) {
                logger.warn("Skipped: {}", id);
            }
        });
    }
}
