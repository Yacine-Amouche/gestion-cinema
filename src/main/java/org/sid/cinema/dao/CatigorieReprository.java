package org.sid.cinema.dao;

import org.sid.cinema.entity.Catigorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface CatigorieReprository extends JpaRepository<Catigorie, Long> {

}
