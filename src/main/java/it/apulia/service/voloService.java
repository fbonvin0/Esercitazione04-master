package it.apulia.service;

import it.apulia.model.Volo;

import java.time.LocalDate;
import java.util.List;

public interface voloService {
    Volo saveVolo(Volo volo);

    List<Volo> getAllVoli();
    Volo getVoloById( String id);
   List<Volo> getVoloByData(LocalDate data_pa);

    Volo getVoloByLuogoPartenza(String citta_destinazione);

    void updateVolo(Volo volo);
    void deleteVolo(String id_volo);
}
