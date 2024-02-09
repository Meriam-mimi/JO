package fr.JO.jeuxOlympiques.stade;


import fr.JO.jeuxOlympiques.epreuve.Epreuve;
import fr.JO.jeuxOlympiques.spectateur.SpectateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadeService {
    private StadeRepository stadeRepository;


    public Stade findById(Integer idStade) {
        return stadeRepository.findById(idStade).orElseThrow();
    }

    public void save(Stade stade) {
        stadeRepository.save(stade);
    }
}
