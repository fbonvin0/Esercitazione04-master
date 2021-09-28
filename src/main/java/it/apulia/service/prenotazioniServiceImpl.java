package it.apulia.service;

import it.apulia.model.Bagagli;
import it.apulia.model.Prenotazione;
import it.apulia.repository.prenotazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class prenotazioniServiceImpl implements prenotazioniService{
    private final prenotazioneRepository prenotazioneRepository;



    @Override
    public Prenotazione savePrenotazione(Prenotazione prenotazione) {
        return this.prenotazioneRepository.save(prenotazione);
    }

    @Override
    public List<Prenotazione> findPrenotazioni(Integer id) {
        return this.prenotazioneRepository.findPrenotazioneById(id);
    }

    @Override
    public List<Prenotazione> getPrenoptazioneByData(LocalDate data_prenotazione) {
        return this.prenotazioneRepository.findPrenotazioneByData(data_prenotazione);
    }

    @Override
    public List<Prenotazione> getAllPrenotazione() {
        return this.prenotazioneRepository.findAll();
    }

    @Override
    public List<Prenotazione> getPrenotazione() {
        return prenotazioneRepository.findAll();
    }
    public boolean controllo(List<Bagagli> l) {
        for (Bagagli b : l) {
            if (b.equals(Bagagli.BAGAGLIO_BASIC)) {
                return true;
            }
        }
        return false;
    }
}


