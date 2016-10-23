package pl.pollub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMoviesListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ImdbFetcher fetcher;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        fetcher.push();
    }
}
