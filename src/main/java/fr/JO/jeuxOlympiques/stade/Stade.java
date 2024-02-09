package fr.JO.jeuxOlympiques.stade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer Id;

    @Column(nullable = false)
    @NotBlank(message = "Ce champ est obligatoire !!")

    private String nom;


    @Column()
    private String lieu;





    // one to many avec epreuve
}
