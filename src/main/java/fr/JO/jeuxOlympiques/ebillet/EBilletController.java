package fr.JO.jeuxOlympiques.ebillet;


import fr.JO.jeuxOlympiques.exception.EntitityNotFoundException;
import fr.JO.jeuxOlympiques.spectateur.Spectateur;
import fr.JO.jeuxOlympiques.spectateur.SpectateurRepository;
import fr.JO.jeuxOlympiques.spectateur.SpectateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billets")
public class EBilletController {

    @Autowired
    private final EBilletRepository eBilletRepository;

    private SpectateurService spectateurService;
    @Autowired
    private EBilletService eBilletService;
    @Autowired
    public EBilletController(EBilletRepository eBilletRepository ,SpectateurService spectateurService) {
        this.eBilletRepository = eBilletRepository;
        this.spectateurService = spectateurService ;
    }



    @GetMapping
    public ResponseEntity<List<EBillet>> getBillets() {
        return new ResponseEntity<>(eBilletRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{idBillet}")
    public ResponseEntity<EBillet> getBillet(@PathVariable String idBillet) {
        EBillet eBillet = eBilletRepository.findById(idBillet).orElseThrow(()->new EntitityNotFoundException("Billet not found"));
        if(eBillet == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(eBillet, HttpStatus.OK);

    }

    @PostMapping
    public void createBillet(@Valid @RequestBody EBillet eBillet) {
        eBilletRepository.save(eBillet);
    }


    @DeleteMapping("/{idBillet}")
    public ResponseEntity<?> deleteBillet(@PathVariable String idBillet) {
        if(!eBilletRepository.existsById(idBillet)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eBilletRepository.deleteById(idBillet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{idBillet}")
    public ResponseEntity<?> updateBillet(@PathVariable String idBillet, @RequestBody EBillet eBillet) {
        EBillet billetAModifier = eBilletRepository.findById(idBillet).orElseThrow(()->new EntitityNotFoundException("Billet not found"));
        if (billetAModifier != null) {
            billetAModifier.setDate_billet(eBillet.getDate_billet());
            billetAModifier.setPrix(eBillet.getPrix());
            billetAModifier.setNbDePersonne(eBillet.getNbDePersonne());
            billetAModifier.setSpectateur(eBillet.getSpectateur());
            eBilletRepository.save(billetAModifier);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/{idBillet}")
    public ResponseEntity<?> updatePartielBillet(@PathVariable String idBillet, @RequestBody EBillet eBillet) {
        EBillet billetAModifier = eBilletRepository.findById(idBillet).orElseThrow(()->new EntitityNotFoundException("Billet not found"));
        if (billetAModifier != null) {
            if(eBillet.getDate_billet() != null){
                billetAModifier.setDate_billet(eBillet.getDate_billet());
            }
            if(eBillet.getPrix() != 0){
                billetAModifier.setPrix(eBillet.getPrix());
            }
            if(eBillet.getNbDePersonne() != 0){
                billetAModifier.setNbDePersonne(eBillet.getNbDePersonne());
            }
            if(eBillet.getSpectateur() !=null){
                billetAModifier.setSpectateur(eBillet.getSpectateur());
            }
            eBilletRepository.save(billetAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /*
    @PatchMapping("/{idBillet}/spectateurs")
    public ResponseEntity<?> ajouterSpectateurs(@PathVariable String idBillet, @RequestBody List<Integer> idSpecs) {
        EBillet eBillet = eBilletService.findById(idBillet);
        List<Spectateur> spectateurList = spectateurService.findAllByIdIn(idSpecs);
        if (eBillet != null && spectateurList != null) {
            eBilletService.ajoutPlusieursSpec(idBillet, idSpecs);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }*/
    @PatchMapping("/{idBillet}/spectateur")
    public ResponseEntity<?> ajouterEnclosAAnimal(@PathVariable String idBillet, @RequestBody AjoutSpectateur ajoutSpectateur) {
        EBillet eBillet = eBilletService.findById(idBillet);
        Spectateur monSpectateur = spectateurService.findById(ajoutSpectateur.spectateurId());
        if(eBillet != null && monSpectateur != null) {
            eBillet.setSpectateur(monSpectateur);
            eBilletService.save(eBillet);
        }
        return ResponseEntity.noContent().build();
    }
}
