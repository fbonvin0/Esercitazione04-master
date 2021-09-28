package it.apulia.config;

import it.apulia.model.*;
import it.apulia.repository.*;
import it.apulia.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


@Configuration
public class Configurazione {

    @Bean
    CommandLineRunner commandLineRunner(voloRepository volorep, voloServiceImpl voloService, utenteRepository userRepo, utenteServiceImpl userImpl,
                                        passeggeroRepository passeggeroRepo, passeggeriServiceImpl passeggeroService,
                                        prenotazioneRepository prenotazioneRepo, prenotazioniServiceImpl prenotazioneImpl,
                                        rolerepository roleRepo, utenteService userService) {
        return args -> {
            userService.resetAll();
            volorep.deleteAll();
            userRepo.deleteAll();
            passeggeroRepo.deleteAll();
            prenotazioneRepo.deleteAll();
            roleRepo.deleteAll();

            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_ADMIN"));

            Passeggero pass1 = new Passeggero("John", "Ring", "john.ring@mail.it", new ArrayList<Contatto>());
            Passeggero pass2 = new Passeggero("Stan", "Smith", "s.smith@mail.it", new ArrayList<Contatto>());
            Passeggero pass3 = new Passeggero("Gianni", "Carry", "carry@mail.it", new ArrayList<Contatto>());
            Passeggero pass4 = new Passeggero("Vito", "Sanvito", "sanvito@mail.it", new ArrayList<Contatto>());

            userService.saveUtente(new Utente(null, "john.ring@mail.it", "john", new ArrayList<>(),"paperino"));
            userService.saveUtente(new Utente(null, "s.smith@mail.it", "will", new ArrayList<>(),"pippo"));
            userService.saveUtente(new Utente(null, "carry@mail.it", "jim", new ArrayList<>(),"minni"));
            userService.saveUtente(new Utente(null, "sanvito@mail.it", "vito", new ArrayList<>(),"inter"));




            userService.aggiungiRuoloUt("john.ring@mail.it", "ROLE_USER");
            userService.aggiungiRuoloUt("s.smith@mail.it", "ROLE_USER");
            userService.aggiungiRuoloUt("carry@mail.it", "ROLE_ADMIN");
            userService.aggiungiRuoloUt("sanvito@mail.it", "ROLE_ADMIN");


         /*   Volo volo1;
            Volo volo2;
            Volo volo3;
            Volo volo4;
            voloService.saveVolo(volo1  = new Volo("AZ208T","Roma", "Bari", "Alitalia", LocalDate.of(2020,01,12), 200, LocalTime.of(15,00), LocalTime.of(14,25), 150));
            voloService.saveVolo(volo2 = new Volo("DF109H", "Palermo", "Roma", "Ryanair",  LocalDate.of(2021,05,12),500,LocalTime.of(5,00),LocalTime.of(10,26), 500));
            voloService.saveVolo(volo3 = new Volo("PL987Y", "Bologna", "Torino", "Ryanair",LocalDate.of(2021,05,12),300, LocalTime.of(02,36), LocalTime.of(9,12), 200));
            voloService.saveVolo(volo4 =  new Volo("AZ208T", "Milano", "Bari", "Ryanair",LocalDate.of(2021,05,13),200, LocalTime.of(18,00), LocalTime.of(20,15), 125));
*/

            Volo volo1 = new Volo("AZ208T","Roma", "Bari", "Alitalia",  LocalDate.of(2020,12,25), 200 , LocalTime.of(15,00), LocalTime.of(14,25), 150);
            Volo volo2  = new Volo("DF109H", "Palermo", "Roma", "Ryanair",  LocalDate.of(2021,05,12),500,LocalTime.of(5,00),LocalTime.of(10,26), 500);
            Volo volo3 = new Volo("PL987Y", "Bologna", "Torino", "Ryanair",LocalDate.of(2021,05,12),300, LocalTime.of(02,36), LocalTime.of(9,12), 200);
            Volo volo4 =  new Volo("AZ208T", "Milano", "Bari", "Ryanair",LocalDate.of(2021,05,13),200, LocalTime.of(18,00), LocalTime.of(20,15), 125);

            ArrayList<Volo> temp = new ArrayList<>();
            temp.add(volo1);
            temp.add(volo2);
            temp.add(volo3);
            temp.add(volo4);

            volorep.saveAll(temp);

            ArrayList<Passeggero> temp2 = new ArrayList<>();
            temp2.add(pass1);
            temp2.add(pass2);
            temp2.add(pass3);
            temp2.add(pass4);

            passeggeroRepo.saveAll(temp2);


            Bagagli bag1 = Bagagli.BAGAGLIO_BASIC;
            Bagagli bag2 = Bagagli.BORSA;
            Bagagli bag3 = Bagagli.TRACOLLA;
            Bagagli bag4 = Bagagli.ZAINO;



            Prenotazione pren1 = new Prenotazione("JR3457", volo1, pass1, LocalDate.of(2019,12,05),bag1);
            Prenotazione pren2 =  new Prenotazione("JR3457", volo2, pass2, LocalDate.of(2029,12,03),bag2);
            Prenotazione pren3 = new Prenotazione("JR3457", volo3, pass3, LocalDate.of(2019,01,12),bag3);
            Prenotazione pren4 = new Prenotazione("JR3457", volo4, pass4, LocalDate.of(2019,05,25),bag4);


            ArrayList<Prenotazione> temp3 = new ArrayList<>();
            temp3.add(pren1);
            temp3.add(pren2);
            temp3.add(pren3);
            temp3.add(pren4);

            prenotazioneRepo.saveAll(temp3);

        };
    }
}