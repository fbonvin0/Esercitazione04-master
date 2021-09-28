package it.apulia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    private String nome;
    public Role(String nome) {
        this.nome = nome;
    }
}
