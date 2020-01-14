package org.sid.cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

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
import org.sid.cinema.entity.Film;
import org.sid.cinema.entity.Place;
import org.sid.cinema.entity.Projection;
import org.sid.cinema.entity.Salle;
import org.sid.cinema.entity.Seance;
import org.sid.cinema.entity.Ticket;
import org.sid.cinema.entity.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
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
		double[] prices = new double[] {50,60,70,80,90,100,110};
		villeRepository.findAll().forEach(ville->{
	ville.getCinema().forEach(cinema ->{
		cinema.getSalles().forEach(salle->{
			filmRepository.findAll().forEach(film->{
				seaneRepository.findAll().forEach(seance->{
					Projection projection = new Projection();
					projection.setDateProjection(new Date());
					projection.setFilm(film);
					projection.setPrix(prices[new Random().nextInt(prices.length)]);
					projection.setSalle(salle);
					projection.setSeance(seance);
					projectionRepository.save(projection);
				});
			});
		});
	});
		});

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
		
		double[] durees=new double[] {1,1.5,2,2.5,3};
		List<Catigorie> catigories =catigorieRepository.findAll();
		Stream.of("Massinissa","Amirouche","Elghola","ftama nsoumar").forEach(titreFilm->{
			Film film = new Film();
			film.setTitre(titreFilm);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(titreFilm.replaceAll(" ", "")+".jpg");
			film.setCatigorie(catigories.get(new Random().nextInt(catigories.size())));
			filmRepository.save(film);
		});

	}

	@Override
	public void InitTickets() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReserve(false);
				ticketRepository.save(ticket);
				
			});	
			
		});
	}

}
