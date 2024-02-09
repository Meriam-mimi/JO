package fr.JO.jeuxOlympiques.spectateur;

import fr.JO.jeuxOlympiques.ebillet.EBillet;
import fr.JO.jeuxOlympiques.exception.EntitityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpectateurService {
    private SpectateurRepository spectateurRepository;

    @Autowired
    public SpectateurService(SpectateurRepository spectateurRepository) {
        this.spectateurRepository = spectateurRepository;
    }

    public void save(Spectateur spectateur) {
        spectateurRepository.save(spectateur);
    }

    public List<Spectateur> findAll() {
      return spectateurRepository.findAll();
    }

    public Spectateur findById(Integer idSpec) {
        Spectateur spectateur = spectateurRepository.findById(idSpec).orElseThrow(()->new EntitityNotFoundException("Spectateur not found"));
        return  spectateur ;
    }

    public boolean existsById(Integer idSpec) {
      return spectateurRepository.existsById(idSpec);
    }

    public void deleteById(Integer idSpec) {
        spectateurRepository.deleteById(idSpec);
    }

    public List<Spectateur> getAllByIds(List<Integer> ids) {
        return spectateurRepository.findAllByIdIn(ids);
    }

    public List<Spectateur> findAllByIdIn(List<Integer> idSpecs) {
        return  spectateurRepository.findAllByIdIn(idSpecs);
    }
/*
    public void save(CreateVeterinaire veterinaire) {
        Veterinaire veterinaireACreer = new Veterinaire();
        veterinaireACreer.setNom(veterinaire.getNom());
        veterinaireACreer.setPrenom(veterinaire.getPrenom());
        veterinaireRepository.save(veterinaireACreer);
    }*/


}
