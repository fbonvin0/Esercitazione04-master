package it.apulia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "volo")
public class Volo  {
    @Id
    String  id_volo;
    String citta_partenza;
    String citta_destinazione;
    String compagnia_volo;
    LocalDate data;
    Integer capienza;
    LocalTime ora_partenza;
    LocalTime ora_arrivo;
    Integer num_passeggeri = 0;


}
