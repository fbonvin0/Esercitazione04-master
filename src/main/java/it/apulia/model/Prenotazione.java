package it.apulia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="prenotazione")
public class Prenotazione {

    String id_prenotazione;
    Volo volo;
    Passeggero passeggero;
    LocalDate data_prenotazione;
    Bagagli bagagli;

}
