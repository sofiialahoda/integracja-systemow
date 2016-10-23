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
import java.util.Scanner;

@Component
public class ImdbFetcher {

    @Autowired
    private MovieRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(ImdbFetcher.class);
    private final String idsPath = "https://raw.githubusercontent.com/rdzhadan/integracja-systemow/master/src/main/resources/ids.file";

    public void push() {
        try {
            Scanner scanner = new Scanner(new URL(idsPath).openStream());
            while (scanner.hasNext()) {
                String id = scanner.nextLine();
                try {
                    String json = fetch(id);
                    Movie movie = mapper.readValue(json, Movie.class);
                    repository.save(movie);
                } catch (Exception e) {
                    logger.warn("Skipped: {}", id);
                }
            }
        } catch (Exception e) {
            logger.error("Can't read ids file. Reason: {}.", e.getMessage());
        }
    }

    private String fetch(String imdbId) throws Exception {
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
}
