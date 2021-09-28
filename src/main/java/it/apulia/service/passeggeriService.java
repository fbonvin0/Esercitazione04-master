package it.apulia.service;

import it.apulia.model.Passeggero;

import java.util.List;

public interface passeggeriService {
    List<Passeggero> findPasseggeroByEmail(String email);

    List<Passeggero> findPasseggeroById(Integer id);

    Passeggero save(Passeggero passeggero);
}
