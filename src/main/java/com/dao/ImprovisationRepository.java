package com.dao;

import com.entities.Improvisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImprovisationRepository extends JpaRepository<Improvisation, Long> {

}
