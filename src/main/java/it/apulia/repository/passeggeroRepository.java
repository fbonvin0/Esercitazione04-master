package it.apulia.repository;

import it.apulia.model.Passeggero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface passeggeroRepository extends MongoRepository<Passeggero, String> {
    @Query("{ 'email' : ?0 }")
    List<Passeggero> findPasseggeroByEmail(String email);

    @Query("{ 'id' : ?0 }")
    List<Passeggero> findPasseggeroById(Integer id);
}
