package it.apulia.service;


import it.apulia.model.Volo;
import it.apulia.repository.voloRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class voloServiceImpl implements voloService {

    public final voloRepository voloRepository;


    @Override
    public Volo saveVolo(Volo volo){
        return voloRepository.save(volo);
    }
    @Override
    public List<Volo> getAllVoli() {
        return voloRepository.findAll();
    }

    @Override
    public Volo getVoloById(String id) {
        return this.voloRepository.findById(id).get();
    }

    @Override
    public List<Volo> getVoloByData(LocalDate data) {
        return (List<Volo>) this.voloRepository.getVoloByData(data);
    }


    @Override
    public Volo getVoloByLuogoPartenza(String citta_destinazione) {
        return this.getVoloByLuogoPartenza(citta_destinazione);
    }

    @Override
    public void updateVolo(Volo volo) {
        if(this.getVoloById(volo.getId_volo()).equals(volo))
            voloRepository.save(volo);
        else
            log.error("Volo da aggiornare {} della compagnia {} non presente all'interno del db", volo.getId_volo(), volo.getCompagnia_volo());
    }

    @Override
    public void deleteVolo(String id_volo) {
        voloRepository.deleteById(id_volo);
    }

}
