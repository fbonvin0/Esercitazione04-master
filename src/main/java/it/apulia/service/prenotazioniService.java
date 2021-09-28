package it.apulia.service;

import it.apulia.model.Prenotazione;

import java.time.LocalDate;
import java.util.List;


public interface prenotazioniService {

    Prenotazione savePrenotazione(Prenotazione prenotazione);
    List<Prenotazione> findPrenotazioni(Integer id);
    List<Prenotazione> getPrenoptazioneByData(LocalDate data_prenotazione);
    List<Prenotazione> getAllPrenotazione();

    List<Prenotazione> getPrenotazione();
}
