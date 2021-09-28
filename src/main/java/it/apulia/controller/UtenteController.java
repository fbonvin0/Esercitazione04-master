package it.apulia.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.apulia.model.Role;
import it.apulia.model.RoleUtente;
import it.apulia.model.Utente;
import it.apulia.service.utenteService;
import it.apulia.service.utenteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UtenteController {
    private final utenteService utenteService;

    @GetMapping("/utenti")
    public ResponseEntity<List<Utente>> getUtenti() {
        return ResponseEntity.ok().body(this.utenteService.getUtenti());
    }

    @PostMapping("/utenti")
    public ResponseEntity<Utente> saveUtente(Utente utente) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/utenti/"+utente.getUsername()).toUriString());
        return ResponseEntity.ok().body(this.utenteService.saveUtente(utente));
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role ruolo) {
        return ResponseEntity.ok().body(this.utenteService.saveRole(ruolo));
    }

    @PostMapping("/roles/addToUtente")
    public ResponseEntity<?> addRoleToUtente(@RequestBody RoleUtente ruolo) {
        this.utenteService.aggiungiRuoloUt(ruolo.getUsername(), ruolo.getRoleName());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Utente user = this.utenteService.getUtente(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getNome).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }

    }
}
