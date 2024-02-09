package fr.JO.jeuxOlympiques.ebillet;

import fr.JO.jeuxOlympiques.epreuve.Epreuve;
import fr.JO.jeuxOlympiques.spectateur.Spectateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


//////////// billet epreuve OneToOne if(pas inscription spec sur la même date)
public class EBillet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private Date date_billet;

    // par default egale à 1
    @Column()
    private Integer nbDePersonne;

    private Double prix ;

    @OneToOne()
    private Spectateur spectateur;

    @ManyToOne
    @JoinColumn(name = "epreuve_id")
    private Epreuve epreuve;
}
