package fr.JO.jeuxOlympiques.epreuve;

import fr.JO.jeuxOlympiques.ebillet.EBillet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Epreuve {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(nullable = false)
    @NotBlank(message = "Ce champ est obligatoire !!")
    private String nom;

    private Date dateTime;
    //private LocalDateTime dateTime;


    @OneToMany(mappedBy = "epreuve")
    private List<EBillet> eBillets = new ArrayList<>();



    // un epreuve peut avoir plusieurs billets
    // one to many (many billet one epreuve)
}
