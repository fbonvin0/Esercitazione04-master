package it.apulia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="passegero")

public class Passeggero {
    String nome;
    String cognome;
    String utente;
    ArrayList<Contatto> contatto;

}

