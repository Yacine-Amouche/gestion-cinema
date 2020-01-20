package org.sid.cinema;

import org.sid.cinema.entity.Film;
import org.sid.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	@Autowired
	private ICinemaInitService iCinemaInitService;
	@Autowired
    private RepositoryRestConfiguration restConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Film.class);
		iCinemaInitService.InitVilles();
		iCinemaInitService.InitCinemas();
		iCinemaInitService.InitSalles();
		iCinemaInitService.InitPlaces();
		iCinemaInitService.InitSeances();
		iCinemaInitService.InitCatigories();
		iCinemaInitService.InitFilms();
		iCinemaInitService.InitProjections();
		iCinemaInitService.InitTickets();
		
	}

}
