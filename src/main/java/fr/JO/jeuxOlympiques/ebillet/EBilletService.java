package fr.JO.jeuxOlympiques.ebillet;


import fr.JO.jeuxOlympiques.spectateur.SpectateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EBilletService {
    @Autowired
    private EBilletRepository eBilletRepository;
    @Autowired
    private SpectateurService spectateurService;
    @Autowired
    public EBilletService(EBilletRepository eBilletRepository, SpectateurService spectateurService) {
        this.eBilletRepository = eBilletRepository;
        this.spectateurService = spectateurService;
    }



    public List<EBillet> getAllByIds(List<Integer> ids) {
        return  eBilletRepository.findAllByIdIn(ids);
    }
    public EBillet getBilletById(String id ){
        return eBilletRepository.findById(id).orElse(null);
    }

    public void save(EBillet eBillet) {
        eBilletRepository.save(eBillet);
    }

    /*public void ajoutPlusieursSpec(String numeroBillet, List<Integer> idSpecs) {
        EBillet billet = getBilletById(numeroBillet);
        List<Spectateur> spectateurs = spectateurService.getAllByIds(idSpecs);
        spectateurs.forEach(spec -> {
            spec.setEBillet(billet);
            eBilletRepository.save(billet);
        });
    }*/

    public EBillet findById(String idBillet) {
        return eBilletRepository.findById(idBillet).orElseThrow();
    }

    public List<EBillet> findAllByIdIn(List<Integer> ids) {
        return eBilletRepository.findAllByIdIn(ids);
    }
}
