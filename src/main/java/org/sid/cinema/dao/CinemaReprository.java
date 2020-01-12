package org.sid.cinema.dao;

import org.sid.cinema.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface CinemaReprository extends JpaRepository<Cinema, Long> {

}
