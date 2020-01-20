package org.sid.cinema.dao;

import org.sid.cinema.entity.Catigorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface CatigorieRepository extends JpaRepository<Catigorie, Long> {

}
