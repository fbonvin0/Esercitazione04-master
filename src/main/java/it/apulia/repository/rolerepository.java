package it.apulia.repository;

import it.apulia.model.Passeggero;
import it.apulia.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface rolerepository extends MongoRepository<Role, String> {
   // @Query("'nome : ?0'")
    Role findByNome(String nome);
}
