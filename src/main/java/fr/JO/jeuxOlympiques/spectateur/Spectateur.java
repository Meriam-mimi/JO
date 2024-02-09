package fr.JO.jeuxOlympiques.spectateur;


import fr.JO.jeuxOlympiques.ebillet.EBillet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spectateur  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer Id;

    @Column(nullable = false)
    private String nom;
    @NotBlank(message = "Ce champ est obligatoire !!")
    @Size(min = 4,max = 15)
    @Column(nullable = false)
    private String prenom;

    @Column(name = "numero_spectateur")
    private String telephone;



    // Un billet pour plusieurs spectacteur

}
