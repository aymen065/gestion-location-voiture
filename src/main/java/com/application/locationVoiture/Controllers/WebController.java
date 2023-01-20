package com.application.locationVoiture.Controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.locationVoiture.Entities.Accident;
import com.application.locationVoiture.Entities.Assurance;
import com.application.locationVoiture.Entities.Client;
import com.application.locationVoiture.Entities.Manager;
import com.application.locationVoiture.Entities.Prix;
import com.application.locationVoiture.Entities.Reservation;
import com.application.locationVoiture.Entities.Voiture;

@Controller
public class WebController {



	@RequestMapping(value = "/")
	public String menu(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "menu";
	}
	
	


	@RequestMapping(value = "/Client/signUp")
	public String signUp(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "signup";
	}

	@RequestMapping(value = "/Client/signUpSuccess")
	public String signUpSuccess(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "signup";
	}

	@RequestMapping(value = "/Manager/signUp")
	public String managersignUp(Model model) {
		Manager manager = new Manager();
		model.addAttribute("manager", manager);
		return "signInManager";
	}

	@RequestMapping(value = "/Client/Reservation")
	public String clientRes(@ModelAttribute Client client, Model model) {
		if (client != null) {
			Reservation reservation = new Reservation();
			model.addAttribute("reservation", reservation);
			return "reservation";
		}
		return null;
	}

	@RequestMapping(value = "/Ajouter/Voiture")
	public String AjouterVoiture(Model model) {

		Voiture voiture = new Voiture();
		model.addAttribute("voiture", voiture);
		return "AjouterVoiture";
	}

	@RequestMapping(value = "/Ajouter/Assurance")
	public String AjouterAssurance(Model model) {

		Assurance assurance = new Assurance();
		model.addAttribute("assurance", assurance);
		return "AjouterAssurance";
	}

	@RequestMapping(value = "/Accueil")
	public String Acceuil(Model model) {

		Voiture voiture = new Voiture();
		model.addAttribute("voiture", voiture);
		return "Accueil";
	}

	@RequestMapping(value = "/Prix")
	public String AjouterPrix(Model model) {

		Prix prix = new Prix();
		model.addAttribute("prix", prix);
		return "AjouterPrix";
	}

	@RequestMapping(value = "/Accident")
	public String AjouterAccident(Model model) {
		Accident accident = new Accident();
		model.addAttribute("accident", accident);
		return "AjouterAccident";
	}

	@RequestMapping(value = "/Annuler/Reservation")
	public String AnnulerRes(@ModelAttribute Client client, Model model) {

		Reservation reservation = new Reservation();
		model.addAttribute("reservation", reservation);
		return "AnnulerReservation";
	}
	
	@RequestMapping(value = "/Modifier/Reservation")
	public String ModifierRes(Model model) {

		Reservation reservation = new Reservation();
		model.addAttribute("reservation", reservation);
		return "ModifierReservation";
	}

}
