package it.apulia.service;

import it.apulia.model.Passeggero;
import it.apulia.repository.passeggeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class passeggeriServiceImpl implements passeggeriService {

    private final passeggeroRepository passeggeroRepository;


    @Override
    public List<Passeggero> findPasseggeroByEmail(String email) {
        return passeggeroRepository.findPasseggeroByEmail(email);
    }

    @Override
    public List<Passeggero> findPasseggeroById(Integer id) {
        return passeggeroRepository.findPasseggeroById(id);
    }

    @Override
    public Passeggero save(Passeggero passeggero) {
        return this.passeggeroRepository.save(passeggero);
    }

}
