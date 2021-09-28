package it.apulia.service;

import it.apulia.model.Role;
import it.apulia.model.Utente;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface utenteService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Utente saveUtente(Utente utente);
    Role saveRole(Role role);
    void aggiungiRuoloUt(String username, String roleName);

    Utente getUtenteRuolo(String username);

    List<Utente> getUtenti();

    Utente getUtente(String username);

    void resetAll();
}
