package spitter.data;

import org.springframework.stereotype.Repository;
import spitter.Spittle;

import java.util.List;

@Repository
public interface SpittleRepository {
    List<Spittle> findSpittles(long maxId, int count);

    Spittle findOne(long id);
}
