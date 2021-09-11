package ar.edu.unq.ttip.alec.backend.repository;


import ar.edu.unq.ttip.alec.backend.model.Responsable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsablesAFIPRepository extends CrudRepository<Responsable, Integer> {

    List<Responsable> findAll();
    Optional<Responsable> getResponsableById(Integer id);

}



