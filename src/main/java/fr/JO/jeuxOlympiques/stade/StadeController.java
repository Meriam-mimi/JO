package fr.JO.jeuxOlympiques.stade;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stades")
public class StadeController {


@Autowired
    private final StadeRepository stadeRepository;

    @Autowired
    public StadeController(StadeRepository stadeRepository) {
        this.stadeRepository = stadeRepository;

    }



    @GetMapping
    public ResponseEntity<List<Stade>> getStades() {
        return new ResponseEntity<>(stadeRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{idStade}")
    public ResponseEntity<Optional> getStade(@PathVariable Integer idStade) {
        Optional<Stade> stade = stadeRepository.findById(idStade);
        if(stade == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(stadeRepository.findById(idStade), HttpStatus.OK);

    }

    @PostMapping
    public void createStade(@Valid @RequestBody Stade stade) {
        stadeRepository.save(stade);
    }


    @DeleteMapping("/{idStade}")
    public ResponseEntity<?> deleteStade(@PathVariable Integer idStade) {
        if(!stadeRepository.existsById(idStade)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stadeRepository.deleteById(idStade);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("/{idStade}")
    public ResponseEntity<?> updateStade(@PathVariable Integer idStade, @RequestBody Stade stade) {
        Stade stadeAModifier = stadeRepository.findById(idStade).orElseThrow();
        if (stadeAModifier != null) {
            stadeAModifier.setNom(stade.getNom());
            stadeAModifier.setLieu(stade.getLieu());
            stadeRepository.save(stadeAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/{idStade}")
    public ResponseEntity<?> updatePartielStade(@PathVariable Integer idStade, @RequestBody Stade stade) {
        Stade stadeAModifier = stadeRepository.findById(idStade).orElseThrow();
        if (stade != null) {
            if(stade.getNom() !=null) {
                stadeAModifier.setNom(stade.getNom());
            }
            if(stade.getLieu() != null) {
                stadeAModifier.setLieu(stade.getLieu());
            }
            stadeRepository.save(stadeAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
