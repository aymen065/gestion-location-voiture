package com.application.locationVoiture.Controllers;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.application.locationVoiture.Entities.Accident;
import com.application.locationVoiture.Entities.Adresse;
import com.application.locationVoiture.Entities.Client;
import com.application.locationVoiture.Entities.Reservation;
import com.application.locationVoiture.Entities.Voiture;
import com.application.locationVoiture.Repositories.AdresseRepository;
import com.application.locationVoiture.Repositories.ClientRepository;
import com.application.locationVoiture.Repositories.ReservationRepository;
import com.application.locationVoiture.Repositories.VoitureRepository;

@RestController
@RequestMapping(value = "/location/Client")
public class ClientController {
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private VoitureRepository voitureRepo;
	@Autowired
	private ReservationRepository reservationRepo;
	@Autowired
	private AdresseRepository adresseRepo;

	@PostMapping(value = "/inscription")
	public ModelAndView inscription(@ModelAttribute Client client, Model model) {
		Client clt = clientRepo.getClt(client.getTel());
		if (clt == null) {
			Adresse adresse = adresseRepo.getAdr(client.getAdresse().getVille(),client.getAdresse().getRue());
			
			  if (adresse == null ) 
				  {
				  adresse = adresseRepo.save(client.getAdresse());				  
				  }
			 
			client.setAdresse(adresse);
			clientRepo.save(client);
			model.addAttribute("client", client);
			return new ModelAndView("signUpSuccess");
		}
		return null;
	}

	@PostMapping(value = "/login")
	public ModelAndView login(@ModelAttribute Client client, Model model) {
		Client clt = clientRepo.getClt(client.getTel() , client.getId());
		if (clt == null) {
			model.addAttribute("client",new Client());
			return new ModelAndView("loginFail");

		}
		model.addAttribute("client", clt);
		return new ModelAndView("menuClient");
	}

	@PostMapping(value = "/reserver")
	public ModelAndView reserver(@ModelAttribute Reservation reservation, Model model) {
		if (reservation.getClient() == null) {
			return null;
		}

		Client client = clientRepo.getCltParId(reservation.getClient().getId());

		if (client == null) {

			model.addAttribute("reservation", reservation);
			return new ModelAndView("ReservationSansId");

		}
		reservation.setClient(client);
		List<Voiture> voitures = voitureRepo.GetVtrParMarque(reservation.getVoiture().getMarque().getNom());
		if (voitures.isEmpty()) {
			return null;
		}
		for (Voiture voiture : voitures) {
			reservation.setVoiture(voiture);
			Reservation res = reservationRepo.getRes(reservation.getVoiture().getMatricule(), reservation.getDateDeb(),
					reservation.getDateFin());
			if (res == null) {

				reservation.setHeure(Time.valueOf(LocalTime.now()));
				reservation = reservationRepo.save(reservation);
				model.addAttribute("reservation", reservation);
				return new ModelAndView("ReservationEffectuee");
			}

		}

		return null;

	}

	@GetMapping(value = "/get/voiture")
	public ModelAndView AfficherVoiture(@ModelAttribute Voiture voiture) {

		List<Voiture> voitures = voitureRepo.GetVtrParMarque(voiture.getMarque().getNom());
		if (voitures.isEmpty()) {
			return new ModelAndView("voitureNonDispo");
		}
		Reservation reservation = new Reservation();
		for (Voiture vtr : voitures) {
			reservation.setVoiture(vtr);
			Reservation res = reservationRepo.getRes(reservation.getVoiture().getMatricule(),
					Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()));
			if (res == null) {
				return new ModelAndView("voitureDispo");
			}

		}

		return new ModelAndView("voitureNonDispo");
		}

	@GetMapping(value = "/Reservations/id/{id}")
	public ModelAndView MesRes(@PathVariable int id, Model model) {

		Client clt = clientRepo.getCltParId(id);
		if (clt == null)
			return null;
		model.addAttribute("reservations", clt.getReservations());
		return new ModelAndView("MesReservations");
	}

	@GetMapping(value = "/Accidents/id/{id}")
	public ModelAndView MesFact(@PathVariable int id, Model model) {

		Client clt = clientRepo.getCltParId(id);
		if (clt == null)
			return null;
		Set<Accident> accidents = clt.getAccidents();

		model.addAttribute("accidents", accidents);
		return new ModelAndView("MesAccidents");
	}

	@PostMapping(value = "/Recherche")
	public Object Recherche(@RequestParam String rech , Model model) {
		List<Voiture> voitures = voitureRepo.GetVtrParMarque(rech);
		if (!(voitures.isEmpty())) {
			List<Object> list = new ArrayList<Object>();
			for (Voiture voiture : voitures) {
				List<Object> etatVoiture = new ArrayList<Object>();
				etatVoiture.add(voiture);
				if (reservationRepo.getRes(voiture.getMatricule(), Date.valueOf(LocalDate.now()),
						Date.valueOf(LocalDate.now())) != null)
					etatVoiture.add("réservée");
				else {
					etatVoiture.add("disponible");
				}
				list.add(etatVoiture);

			}
			model.addAttribute("voitures", list);
			return new ModelAndView("listeVoiture");
		}
		voitures = voitureRepo.GetVtrParCat(rech);
		if (!(voitures.isEmpty())) {
			List<Object> list = new ArrayList<Object>();
			for (Voiture voiture : voitures) {
				List<Object> etatVoiture = new ArrayList<Object>();
				etatVoiture.add(voiture);
				if (reservationRepo.getRes(voiture.getMatricule(), Date.valueOf(LocalDate.now()),
						Date.valueOf(LocalDate.now())) != null)
					etatVoiture.add("réservée");
				else {
					etatVoiture.add("disponible");
				}
				list.add(etatVoiture);

			}
			model.addAttribute("voitures", list);
			return new ModelAndView("listeVoiture");
		}

		voitures = voitureRepo.GetVtrParConst(rech);

		if (!(voitures.isEmpty())) {
			List<Object> list = new ArrayList<Object>();
			for (Voiture voiture : voitures) {
				List<Object> etatVoiture = new ArrayList<Object>();
				etatVoiture.add(voiture);
				if (reservationRepo.getRes(voiture.getMatricule(), Date.valueOf(LocalDate.now()),
						Date.valueOf(LocalDate.now())) != null)
					etatVoiture.add("réservée");
				else {
					etatVoiture.add("disponible");
				}
				list.add(etatVoiture);

			}
			model.addAttribute("voitures", list);
			return new ModelAndView("listeVoiture");
		}

		
		

		return new ModelAndView("RechercheInvalide");
	}

}
