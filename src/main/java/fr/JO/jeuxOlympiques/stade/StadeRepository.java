package fr.JO.jeuxOlympiques.stade;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StadeRepository extends CrudRepository<Stade,Integer> {

     List<Stade> findAll();
}
