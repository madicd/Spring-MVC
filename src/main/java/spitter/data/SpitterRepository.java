package spitter.data;

import org.springframework.stereotype.Repository;
import spitter.Spitter;

@Repository
public interface SpitterRepository {
    void save(Spitter spitter);

    Spitter findByUsername(String username);
}
