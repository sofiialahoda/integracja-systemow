package pl.pollub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

    @Autowired
    private DatabaseSaver saver;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
