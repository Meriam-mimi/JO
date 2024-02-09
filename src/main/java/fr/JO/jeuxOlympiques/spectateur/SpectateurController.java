package fr.JO.jeuxOlympiques.spectateur;

import fr.JO.jeuxOlympiques.ebillet.EBillet;
import fr.JO.jeuxOlympiques.ebillet.EBilletRepository;
import fr.JO.jeuxOlympiques.ebillet.EBilletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/spectateurs")
public class SpectateurController {

    private final SpectateurService spectateurService;
    private EBilletRepository eBilletRepository;

    @Autowired
    public SpectateurController(SpectateurService spectateurService) {
        this.spectateurService = spectateurService;
    }

    @GetMapping
    public ResponseEntity<List<Spectateur>> getSpectateurs() {
        return new ResponseEntity<>(spectateurService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{idSpec}")
    public ResponseEntity<Spectateur> getSpectateur(@PathVariable Integer idSpec) {
        Spectateur spectateur = spectateurService.findById(idSpec);
        if(spectateur == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(spectateurService.findById(idSpec), HttpStatus.OK);

    }

    @PostMapping
    public void createSpectateur(@Valid @RequestBody Spectateur spectateur) {
        spectateurService.save(spectateur);
    }


    @DeleteMapping("/{idSpec}")
    public ResponseEntity<?> deleteSpectateur(@PathVariable Integer idSpec) {
        if(!spectateurService.existsById(idSpec)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        spectateurService.deleteById(idSpec);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("/{idSpec}")
    public ResponseEntity<?> updateSpectateur(@PathVariable Integer idSpec, @RequestBody Spectateur spectateur) {
        Spectateur spectateurAModifier = spectateurService.findById(idSpec);
        if (spectateurAModifier != null) {
            spectateurAModifier.setNom(spectateur.getNom());
            spectateurAModifier.setPrenom(spectateur.getPrenom());
            spectateurAModifier.setTelephone(spectateur.getTelephone());
            spectateurService.save(spectateurAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@PatchMapping("/{idSpec}/billet")
    public ResponseEntity<?> ajouterBilletASpectateur(@PathVariable Integer idSpec, @RequestBody String numeroBillet) {
        Spectateur spectateur = spectateurService.findById(idSpec);
       EBillet monBillet =  eBilletRepository.findById(numeroBillet).orElse(null);
        if(spectateur != null && monBillet != null) {
            spectateur.setEBillet(monBillet);

            spectateurService.save(spectateur);
        }
        return ResponseEntity.noContent().build();
        // ResponseEntity response = new ResponseEntity(NO_CONTENT);
    }*/


    @PatchMapping("/{idSpec}")
    public ResponseEntity<?> updatePartielSpectateur(@PathVariable Integer idSpec, @RequestBody Spectateur spectateur) {
        Spectateur spectateurAModifier = spectateurService.findById(idSpec);
        if (spectateurAModifier != null) {
            if(spectateur.getNom() !=null)
                spectateurAModifier.setNom(spectateur.getNom());
            if(spectateur.getPrenom() != null)
                spectateurAModifier.setPrenom(spectateur.getPrenom());
            if(spectateur.getTelephone() != null)
                spectateurAModifier.setTelephone(spectateur.getTelephone());
            spectateurService.save(spectateurAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
