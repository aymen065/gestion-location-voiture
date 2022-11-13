package com.application.locationVoiture.Controllers;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.application.locationVoiture.Entities.Accident;
import com.application.locationVoiture.Entities.Adresse;
import com.application.locationVoiture.Entities.Assurance;
import com.application.locationVoiture.Entities.Categorie;
import com.application.locationVoiture.Entities.Client;
import com.application.locationVoiture.Entities.Constructeur;
import com.application.locationVoiture.Entities.Manager;
import com.application.locationVoiture.Entities.Marque;
import com.application.locationVoiture.Entities.Prix;
import com.application.locationVoiture.Entities.Reservation;
import com.application.locationVoiture.Entities.Voiture;
import com.application.locationVoiture.Repositories.AccidentRepository;
import com.application.locationVoiture.Repositories.AdresseRepository;
import com.application.locationVoiture.Repositories.AssuranceRepository;
import com.application.locationVoiture.Repositories.CategorieRepository;
import com.application.locationVoiture.Repositories.ClientRepository;
import com.application.locationVoiture.Repositories.ConstructeurRepository;
import com.application.locationVoiture.Repositories.ManagerRepository;
import com.application.locationVoiture.Repositories.MarqueRepository;
import com.application.locationVoiture.Repositories.PrixRepository;
import com.application.locationVoiture.Repositories.ReservationRepository;
import com.application.locationVoiture.Repositories.VoitureRepository;

@RestController
@RequestMapping(value = "/location/Manager")
public class ManagerController {
	@Autowired
	private VoitureRepository voitureRepo;

	@Autowired
	private CategorieRepository categorieRepo;

	@Autowired
	private ManagerRepository managerRepo;
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private AccidentRepository accidentRepo;
	@Autowired
	private ConstructeurRepository constructeurRepo;
	@Autowired
	private AdresseRepository adresseRepo;
	@Autowired
	private MarqueRepository marqueRepo;
	@Autowired
	private AssuranceRepository assuranceRepo;
	@Autowired
	private ReservationRepository reservationRepo;
	@Autowired
	private PrixRepository prixRepo;

	

	@PostMapping(value = "/voiture")
	public ModelAndView createVoiture(@ModelAttribute Voiture voiture, Model model) {
		if (voiture.getMatricule() == null || voiture.getMatricule().equals(""))
		{
			model.addAttribute("voiture", voiture);
			return new ModelAndView("ajoutervoiturefail");

		}

		Constructeur cst = constructeurRepo.getConst(voiture.getMarque().getConstructeur().getNom());
		if (cst == null) {
			cst = voiture.getMarque().getConstructeur();
			constructeurRepo.save(cst);

		}
		voiture.getMarque().setConstructeur(cst);

		Marque mq = marqueRepo.getMq(voiture.getMarque().getNom());
		if (mq == null) {
			mq = voiture.getMarque();
			marqueRepo.save(mq);
		}

		voiture.setMarque(mq);
		Categorie cat = categorieRepo.getCat(voiture.getCategorie().getNom());
		if (cat == null) {
			cat = voiture.getCategorie();
			categorieRepo.save(cat);

		}
		voiture.setCategorie(cat);
		voiture = voitureRepo.save(voiture);
		model.addAttribute("voiture", voiture);
		return new ModelAndView("VoitureAjoutee");
	}

	@PostMapping(value = "/assurance")
	public ModelAndView createAssurance(@ModelAttribute Assurance assurance, Model model) {
		Voiture voiture = voitureRepo.GetVtr(assurance.getVoiture().getMatricule());
		if (voiture == null)
			return null;
		assuranceRepo.save(assurance);
		assurance.setVoiture(voiture);
		model.addAttribute("assurance", assurance);
		return new ModelAndView("AssuranceAjoutee");
	}

	@PostMapping(value = "/login")
	public ModelAndView login(@ModelAttribute Manager manager, Model model) {
		Manager mg = managerRepo.getMg(manager.getId());
		if (mg == null) {
			return new ModelAndView("loginManagerFail");
		}
		model.addAttribute("nbClient", nbClient());
		model.addAttribute("nbRes", nbReservation());
		model.addAttribute("nbVtr", nbvoiture());
		return new ModelAndView("management");
	}

	@PostMapping(value = "/marque")
	public Marque createMarque(@RequestBody Marque marque) {
		Marque mq = marqueRepo.getMq(marque.getNom());
		if (mq == null) {
			Constructeur cst = constructeurRepo.getConst(marque.getConstructeur().getNom());
			if (cst == null)
				return null;
			marque.setConstructeur(cst);
			return marqueRepo.save(marque);
		}

		return null;

	}

	@PostMapping(value = "/categorie")
	public Categorie createCategorie(@RequestBody Categorie categorie) {
		Categorie cat = categorieRepo.getCat(categorie.getNom());
		if (cat == null)
			return categorieRepo.save(categorie);
		return null;

	}

	@PostMapping(value = "/constructeur")
	public Constructeur createConstructeur(@RequestBody Constructeur constructeur) {
		Constructeur cst = constructeurRepo.getConst(constructeur.getNom());
		if (cst == null)
			return constructeurRepo.save(constructeur);
		return null;

	}

	@PostMapping(value = "/prix")
	public ModelAndView createPrix(@ModelAttribute Prix prix, Model model) {
		if (prix.getDateDeb() == null || prix.getDateFin() == null)
			return null;
		if (prix.getCategorie().getNom() == null)
			return null;
		Categorie cat = categorieRepo.getCat(prix.getCategorie().getNom());
		if (cat == null)
			return null;
		prix.setCategorie(cat);
		Prix px = prixRepo.getPx(cat, prix.getDateDeb(), prix.getDateFin());
		if (px == null) {

			prix = prixRepo.save(prix);
			model.addAttribute("prix", prix);
			return new ModelAndView("PrixAjoute");
		}

		return null;

	}

	@PostMapping(value = "/accident")
	public ModelAndView createAccident(@ModelAttribute Accident accident, Model model) {
		Client clt = clientRepo.getClt(accident.getClient().getTel());
		if (accident.getDate() == null)
			return null;
		if (clt == null) {
			return null;
		}
		accident.setClient(clt);
		Voiture voiture = voitureRepo.GetVtr(accident.getVoiture().getMatricule());
		if (voiture == null)
			return null;
		accident.setVoiture(voiture);

		Manager manager = managerRepo.getMg(accident.getManager().getId());
		if (manager == null)
			return null;
		accident.setManager(manager);
		accidentRepo.save(accident);
		model.addAttribute("accident", accident);
		return new ModelAndView("AccidentAjoute");

	}

	@GetMapping(value = "/clients/par/ville")
	public List<Client> afficherClient(@RequestBody Adresse adresse) {
		Adresse adr = adresseRepo.getAdr(adresse.getVille());
		List<Client> clients = clientRepo.getByAdresse(adr);
		return clients;
	}

	@GetMapping(value = "/nb/clients")
	public int nbClient() {
		return clientRepo.getNbClt();
	}

	@GetMapping(value = "/nb/reservations")
	public int nbReservation() {
		return reservationRepo.getNbRes(Date.valueOf(LocalDate.now()));
	}

	@GetMapping(value = "/nb/voitures")
	public int nbvoiture() {
		return voitureRepo.getNbVtr();
	}

	@PostMapping(value = "/delete/reservation")
	public ModelAndView deleteRes(@ModelAttribute Reservation reservation, Model model) {
		Reservation res = reservationRepo.getResAnnulee(reservation.getVoiture().getMatricule(),
				reservation.getDateDeb(), reservation.getDateFin());
		if (res == null)
			return null;
		model.addAttribute("reservation", reservation);
		reservationRepo.delete(res);

		return new ModelAndView("ReservationAnnulee");
	}

	@PostMapping(value = "/Modifier/reservation")
	public ModelAndView modifierRes(@ModelAttribute Reservation reservation, @RequestParam Date dateFinale,
			@RequestParam Date dateDebut, Model model) {
		Reservation res = reservationRepo.getRes(reservation.getVoiture().getMatricule(), reservation.getDateDeb(),
				reservation.getDateFin());
		if (res == null) {
			return new ModelAndView("ReservationnNull");

		}
		res.setDateDeb(dateDebut);
		res.setDateFin(dateFinale);

		Voiture voiture = res.getVoiture();
		Reservation resv = reservationRepo.getRes(voiture.getMatricule(), dateDebut, dateFinale);
		if (resv == null || resv == res) {

			res.setHeure(Time.valueOf(LocalTime.now()));
			reservationRepo.save(res);
			return new ModelAndView("ReservationModifiee");
		}
		return new ModelAndView("ReservationnNull");
	}

	@PostMapping(value = "/Recherche")
	public Object Recherche(@RequestParam String rech, Model model) {
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

		List<Client> listClt = clientRepo.getCltParNom(rech);
		if (!(listClt.isEmpty())) {
			model.addAttribute("clients", listClt);
			return new ModelAndView("AffListClient");
		}
		listClt = clientRepo.getCltParPrenom(rech);
		if (!(listClt.isEmpty())) {
			model.addAttribute("clients", listClt);
			return new ModelAndView("AffListClient");
		}

		Client clt = clientRepo.getClt(Integer.parseInt(rech));
		if (clt != null) {
			model.addAttribute("client", clt);
			return new ModelAndView("AffClient");
		}

		clt = clientRepo.getCltParId(Integer.parseInt(rech));
		if (clt != null) {
			model.addAttribute("client", clt);
			return new ModelAndView("AffClient");
		}

		return new ModelAndView("RechercheInvalide");
	}

}
