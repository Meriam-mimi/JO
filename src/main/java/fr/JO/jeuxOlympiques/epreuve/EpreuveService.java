package fr.JO.jeuxOlympiques.epreuve;

import fr.JO.jeuxOlympiques.ebillet.CreateEpreuve;
import fr.JO.jeuxOlympiques.ebillet.EBillet;
import fr.JO.jeuxOlympiques.ebillet.EBilletRepository;
import fr.JO.jeuxOlympiques.ebillet.EBilletService;
import fr.JO.jeuxOlympiques.exception.EntitityNotFoundException;
import fr.JO.jeuxOlympiques.spectateur.Spectateur;
import fr.JO.jeuxOlympiques.spectateur.SpectateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EpreuveService {
    private EpreuveRepository epreuveRepository;
    private EBilletService eBilletService;

    public EpreuveService(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }

    public void save(Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }
    public void save(CreateEpreuve epreuve) {
        Epreuve epruveACreer = new Epreuve();
        epruveACreer.setNom(epreuve.getNom());
        epruveACreer.setDateTime(epreuve.getDateTime());
        epreuveRepository.save(epruveACreer);
    }

    public List<Epreuve> findAll() {
        return (List<Epreuve>) epreuveRepository.findAll();
    }

    public Epreuve findById(Integer idEp) {
        Epreuve epreuve = epreuveRepository.findById(idEp).orElseThrow(()->new EntitityNotFoundException("Spectateur not found"));
        return  epreuve ;
    }

    public boolean existsById(Integer idEp) {
        return epreuveRepository.existsById(idEp);
    }

    public void deleteById(Integer idEp) {
        epreuveRepository.deleteById(idEp);
    }

    /*public List<Epreuve> getAllByIds(List<Integer> ids) {
        return epreuveRepository.findAllByIdIn(ids);
    }*/
    public List<EBillet> getAllByIds(List<Integer> ids) {
        return eBilletService.findAllByIdIn(ids);
    }

    public void ajouterBillets(@PathVariable Integer idEpreuve, @RequestBody AjoutBillets ajoutBillets) {
        Epreuve epreuve = getEpreuveById(idEpreuve);
        List<EBillet> eBillets = eBilletService.getAllByIds(ajoutBillets.getIdBillets());
        eBillets.forEach(billet -> {
            billet.setEpreuve(epreuve);
            eBilletService.save(billet);
        });
    }

    private Epreuve getEpreuveById(Integer idEpreuve) {
        return  epreuveRepository.findById(idEpreuve).orElseThrow();
    }
}
