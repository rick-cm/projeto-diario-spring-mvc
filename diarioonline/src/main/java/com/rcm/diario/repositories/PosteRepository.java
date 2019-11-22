package com.rcm.diario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcm.diario.models.Poste;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long>{

}
