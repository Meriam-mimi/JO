package fr.JO.jeuxOlympiques.epreuve;


import fr.JO.jeuxOlympiques.ebillet.CreateEpreuve;
import fr.JO.jeuxOlympiques.spectateur.Spectateur;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epreuves")
public class EpreuveController {

    @Autowired
    private final EpreuveService epreuveService;
    @Autowired
    private final EpreuveRepository epreuveRepository;
    @Autowired
    public EpreuveController(EpreuveService epreuveService, EpreuveRepository epreuveRepository) {
        this.epreuveService = epreuveService;
        this.epreuveRepository = epreuveRepository;
    }



    @GetMapping
    public ResponseEntity<List<Epreuve>> getEpreuves() {
        return new ResponseEntity<>(epreuveService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{idEpreuve}")
    public ResponseEntity<Epreuve> getEpreuve(@PathVariable Integer idEpreuve) {
        Epreuve epreuve = epreuveRepository.findById(idEpreuve).orElseThrow();
        if(epreuve == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(epreuveRepository.findById(idEpreuve).orElseThrow(), HttpStatus.OK);

    }

    /*@PostMapping
    public void createEpreuve(@Valid @RequestBody Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }*/
    @PostMapping
    public ResponseEntity<?> createEpreuve(@RequestBody CreateEpreuve epreuve) {
        epreuveService.save(epreuve);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEpreuve}")
    public ResponseEntity<?> deleteEpreuve(@PathVariable Integer idEpreuve) {
        if(!epreuveRepository.existsById(idEpreuve)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        epreuveRepository.deleteById(idEpreuve);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("/{idEpreuve}")
    public ResponseEntity<?> updateEpreuve(@PathVariable Integer idEpreuve, @RequestBody Epreuve epreuve) {
        Epreuve epreuveAModifier = epreuveRepository.findById(idEpreuve).orElseThrow();
        if (epreuveAModifier != null) {
            epreuveAModifier.setNom(epreuve.getNom());
            epreuveAModifier.setDateTime(epreuve.getDateTime());
            epreuveRepository.save(epreuveAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/{idEpreuve}")
    public ResponseEntity<?> updatePartielEpreuve(@PathVariable Integer idEpreuve, @RequestBody Epreuve epreuve) {
        Epreuve epreuveAModifier = epreuveRepository.findById(idEpreuve).orElseThrow();
        if (epreuveAModifier != null) {
            if(epreuve.getNom() !=null)
                epreuveAModifier.setNom(epreuve.getNom());
            if(epreuve.getDateTime() != null)
                epreuveAModifier.setDateTime(epreuve.getDateTime());

            epreuveRepository.save(epreuveAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/{idEpreuve}/billets")
    public ResponseEntity<?> ajouterBillets(@PathVariable Integer idEpreuve, @RequestBody AjoutBillets ajoutBillets) {
        epreuveService.ajouterBillets(idEpreuve, ajoutBillets);
        return ResponseEntity.noContent().build();
    }
}
