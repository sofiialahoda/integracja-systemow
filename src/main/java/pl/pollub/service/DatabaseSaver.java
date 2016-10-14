package pl.pollub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.pollub.service.model.Player;
import pl.pollub.service.model.PlayerDTO;
import pl.pollub.service.repository.PlayerRepository;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

@Component
class DatabaseSaver implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PlayerRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(DatabaseSaver.class);

    private final int fromYear = 1930;
    private final int toYear = 2004;

    private final HashSet<String> set = new HashSet<>();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (isDatabaseNotInitialized()) {
            init();
        }
    }

    private boolean isDatabaseNotInitialized() {
        return repository.count() == 0;
    }

    private void init() {
        try {
            for (int year = fromYear; year <= toYear; year += 4) {
                URL location = getClass().getClassLoader().getResource(year + ".json");
                if (location != null) {
                    File resource = new File(location.toURI());
                    List<PlayerDTO> players = mapper.readValue(resource, mapper.getTypeFactory().constructCollectionType(List.class, PlayerDTO.class));
                    players.forEach(new Consumer<PlayerDTO>() {
                        @Override
                        public void accept(PlayerDTO playerDto) {
                            try {
                                Player player = convert(playerDto);
                                repository.save(player);
                            } catch (Exception e) {
                                logger.error("{} object has invalid fields. Let's skip it. :)", playerDto);
                            }
                        }
                    });
                }
            }
            System.out.println(repository.count());
        } catch (Exception e) {
            logger.error("Database initialization has been failed.", e);
        }
    }

    private Player convert(PlayerDTO dto) {
        Player player = new Player();
        player.setClub(dto.getClub());
        player.setClubCountry(dto.getClubCountry());
        player.setDateOfBirth(dto.getDateOfBirth());
        player.setFullName(dto.getFullName());
        player.setCaptain(dto.getCaptain());
        player.setNumber(Integer.parseInt(dto.getNumber()));
        player.setEyesColor("black");
        player.setHeight(180);
        player.setWeight(75);
        player.setPosition(dto.getPosition());
        player.setWorldCups(3);
        player.setTeam(dto.getTeam());
        return player;
    }
}