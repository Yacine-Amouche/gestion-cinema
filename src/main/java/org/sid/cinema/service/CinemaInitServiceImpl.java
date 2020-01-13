package org.sid.cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import org.sid.cinema.dao.CatigorieRepository;
import org.sid.cinema.dao.CinemaReprository;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.PlaceRepository;
import org.sid.cinema.dao.ProjectionRepository;
import org.sid.cinema.dao.SalleRepository;
import org.sid.cinema.dao.SeanceRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.dao.VilleRepository;
import org.sid.cinema.entity.Catigorie;
import org.sid.cinema.entity.Cinema;
import org.sid.cinema.entity.Place;
import org.sid.cinema.entity.Salle;
import org.sid.cinema.entity.Seance;
import org.sid.cinema.entity.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CinemaInitServiceImpl implements ICinemaInitService {
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CinemaReprository cinemaReprository;
	@Autowired
	private SalleRepository salleRepository; 
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private SeanceRepository seaneRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private CatigorieRepository catigorieRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	

	

	@Override
	public void InitCinemas() {
		villeRepository.findAll().forEach(v->{
			Stream.of("port saint lazard","Goraya","Les oliviers").forEach(nameCinema ->{
				Cinema cinema = new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalle(3+(int)(Math.random()*10));
				cinema.setVille(v);
				cinemaReprository.save(cinema);
			});
		});

	}

	@Override
	public void InitCatigories() {
		Stream.of("Histoire","Guerre","Fiction").forEach(cat->{
			Catigorie catigorie = new Catigorie();
			catigorie.setName(cat);
			catigorieRepository.save(catigorie);
		});
	

	}

	@Override
	public void InitPlaces() {
		salleRepository.findAll().forEach(salle->{
			for(int i =0;i<salle.getNombrePlace();i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});

	}

	@Override
	public void InitProjections() {
		// TODO Auto-generated method stub

	}

	@Override
	public void InitSalles() {
		cinemaReprository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalle();i++) {
				Salle salle = new Salle();
				salle.setName("Salle " +(i+1));
				salle.setCinema(cinema);
				salle.setNombrePlace(20 +(int)(Math.random()*30));
				salleRepository.save(salle);
			}
		});

	}

	@Override
	public void InitSeances() {
		DateFormat dateFormat = new SimpleDateFormat("HH:MM");
		Stream.of("12:05","13:05","15:05","17:05","18:05","19:05","20:05","21:05","22:05").forEach(s->{
		Seance seance = new Seance();
		try {
			seance.setHeurDebut(dateFormat.parse(s));
			seaneRepository.save(seance);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		});
	}

	@Override
	public void InitVilles() {
		Stream.of("Bejaia", "Alger", "Tizi Ouzou", "Oran", "Annaba").forEach(nemeVille -> {
			Ville ville = new Ville();
			ville.setName(nemeVille);
			villeRepository.save(ville);
		});
	}

	@Override
	public void InitFilms() {
		// TODO Auto-generated method stub

	}

	@Override
	public void InitTickets() {
		// TODO Auto-generated method stub

	}

}
