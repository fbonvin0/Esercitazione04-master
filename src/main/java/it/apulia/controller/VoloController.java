package it.apulia.controller;

import it.apulia.model.Volo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping (path = "/voloController")
public class VoloController {

    public  final it.apulia.service.voloService voloService;


    @GetMapping
    ResponseEntity<List<Volo>> getAllVoli() {
        return ResponseEntity.ok().body(voloService.getAllVoli());
    }

    @PostMapping("/addVolo")
    ResponseEntity<Volo> saveVolo(@RequestBody Volo volo){
        voloService.saveVolo(volo);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/voloController/" + volo.getId_volo()).toUriString());

        log.info("Volo {} {} salvato all'interno del tabellone raggiungibile al link {} ",volo.getId_volo(), volo.getCompagnia_volo(), uri.toString());
        return ResponseEntity.created(uri).body(volo);
    }

    @GetMapping("/{id_volo}")
    ResponseEntity<Volo> getVolo(@PathVariable String id_volo) {
        return ResponseEntity.ok().body(voloService.getVoloById(id_volo));
    }

    @PutMapping("/{id_volo}")
    ResponseEntity<?> updateVolo(@PathVariable String id_volo, @RequestBody Volo volo){
        voloService.updateVolo(volo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id_volo}")
    ResponseEntity<?> deleteLibro(@PathVariable String id_volo){
        voloService.deleteVolo(id_volo);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/listaVoliByData")
    ResponseEntity<List<Volo>> findVoloByData(@PathVariable LocalDate data) {
        return ResponseEntity.ok().body(voloService.getVoloByData(data));
    }




}
