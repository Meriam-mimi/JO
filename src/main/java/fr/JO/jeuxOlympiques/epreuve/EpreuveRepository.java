package fr.JO.jeuxOlympiques.epreuve;

import fr.JO.jeuxOlympiques.spectateur.Spectateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EpreuveRepository extends CrudRepository<Epreuve,Integer> {
    List<Epreuve> findAllByIdIn(List<Integer> ids);
    List<Epreuve> findAll();
}
