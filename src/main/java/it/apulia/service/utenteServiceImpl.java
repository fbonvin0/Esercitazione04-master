package it.apulia.service;

import it.apulia.model.Role;
import it.apulia.model.Utente;
import it.apulia.repository.rolerepository;
import it.apulia.repository.utenteRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class utenteServiceImpl implements utenteService, UserDetailsService {
    private final utenteRepository repositoryUtente;
    private final rolerepository roleRepository;
    private final PasswordEncoder passwordEncoder;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = this.repositoryUtente.findUtenteByUsername(username);
        if(utente == null) {
            log.error("Utente non trovato all'interno del db!");
            throw new UsernameNotFoundException("Utente non trovato all'interno del db!");
        } else {
            log.info("Utente richiesto trovato nel db: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            utente.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getNome()));
            });

            return new User(utente.getUsername(), utente.getPassword(), authorities);
        }
    }


    @Override
    public Utente saveUtente(Utente utente) {
        log.info("Salvataggio dell'utente {} all'interno del database", utente.getUsername());

        utente.getRoles().forEach(
                role -> {
                    String nome = role.getNome();
                    role.setId(roleRepository.findByNome(nome).getId());
                }
        );
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return this.repositoryUtente.save(utente);
    }


    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void aggiungiRuoloUt(String username, String roleName) {
    Utente utente = this.repositoryUtente.findUtenteByUsername(username);
    Role role = this.roleRepository.findByNome(roleName);
        log.info("Aggiungo il ruolo {} all'utente {}.",role.getNome(),utente.getUsername());
        utente.getRoles().add(role);

        this.repositoryUtente.deleteById(utente.getId());
        this.repositoryUtente.save(utente);

}

    @Override
    public Utente getUtenteRuolo(String username) {
        this.repositoryUtente.findUtenteByUsername(username).getRoles().forEach(
                ruolo -> { System.out.println(ruolo.getNome());}
        );
        return this.repositoryUtente.findUtenteByUsername(username);
    }
    @Override
    public List<Utente> getUtenti() {
        return this.repositoryUtente.findAll();
    }

    @Override
    public Utente getUtente(String username) {
        this.repositoryUtente.findUtenteByUsername(username).getRoles().forEach(
                ruolo -> { System.out.println(ruolo.getNome());}
        );
        return this.repositoryUtente.findUtenteByUsername(username);
    }


    @Override
    public void resetAll() {
        this.repositoryUtente.deleteAll();
        this.roleRepository.deleteAll();
    }
}
