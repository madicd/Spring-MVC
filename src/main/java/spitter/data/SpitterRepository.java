package spitter.data;

import org.springframework.stereotype.Repository;
import spitter.Spitter;

@Repository
public interface SpitterRepository {
    Spitter save(Spitter spitter);
}
