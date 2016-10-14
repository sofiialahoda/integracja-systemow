package pl.pollub.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.service.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
