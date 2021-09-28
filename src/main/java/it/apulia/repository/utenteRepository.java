package it.apulia.repository;

import it.apulia.model.Passeggero;
import it.apulia.model.Utente;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface utenteRepository extends MongoRepository<Utente, String> {
 //   Utente findByEmail(String email);

    Utente findUtenteByUsername(String username);

    void deleteAll();

    List<Utente> findAll();

    void deleteById(String id);

}
