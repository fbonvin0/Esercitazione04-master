package it.apulia.repository;


import it.apulia.model.Volo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface voloRepository extends MongoRepository<Volo, String> {

    @Query(fields="{ capienza : 0 }")
    Volo  getVoloByData(LocalDate data);
}
