package fr.JO.jeuxOlympiques.ebillet;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EBilletRepository extends CrudRepository<EBillet,String> {
    List<EBillet> findAll();

    List<EBillet> findAllByIdIn(List<Integer> ids);

    Optional<EBillet> findById(String idBillet) ;


    //List<EBillet> findAllByIdIn(List<String> ids);
}
