package it.apulia.repository;

import it.apulia.model.Prenotazione;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface prenotazioneRepository extends MongoRepository<Prenotazione, String> {
    @Query("{ 'data_pa' : ?0 }")
    List<Prenotazione> findPrenotazioneByData(LocalDate data_pa);

    @Query("{ 'id' : ?0 }")
    List<Prenotazione> findPrenotazioneById(Integer id);
}
