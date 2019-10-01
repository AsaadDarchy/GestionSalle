package com.tenor.tsf.gs.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tenor.tsf.gs.entity.Materiel;
@Repository
public interface MaterielRepo extends CrudRepository<Materiel, Long>{

}
