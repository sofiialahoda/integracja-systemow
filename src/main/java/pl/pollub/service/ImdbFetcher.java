package pl.pollub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pollub.service.model.Movie;
import pl.pollub.service.repository.MovieRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Component
public class ImdbFetcher {

    @Autowired
    private MovieRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

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
        URL location = getClass().getClassLoader().getResource("ids.log");
        if (location != null) {
            File resource = new File(location.toURI());
            Scanner scanner = new Scanner(resource);
            while (scanner.hasNext()) {
                String id = scanner.nextLine();
                String json = fetch(id);
                Movie movie = mapper.readValue(json, Movie.class);
                repository.save(movie);
            }
        }
    }
}
