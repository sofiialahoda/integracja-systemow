package pl.pollub.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.service.model.Player;
import pl.pollub.service.repository.PlayerRepository;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository repository;

    @RequestMapping("/players")
    public Iterable<Player> playerList() {
        return repository.findAll();
    }
}