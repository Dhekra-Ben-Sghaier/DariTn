package tn.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.entities.Annonce;
import tn.esprit.entities.File;
import tn.esprit.services.IAnnonceService;



@CrossOrigin(origins ="http://localhost:4200")
@Controller
public class AnnonceController {

	@Autowired
	IAnnonceService annonceService;

	@GetMapping("/retrieveAnnonce")
	@ResponseBody
	public List<Annonce> retrieveAnnonce() {
	 List<Annonce> listAnnonce = annonceService.retrieveAllAnnonces();
	 return listAnnonce;
	 }
	 @GetMapping("/retrieveAnnonce/{id}")
	 @ResponseBody
	 public Annonce retrieveAnnonce(@PathVariable("id") Long idAnnonce) {
	 return annonceService.retrieveAnnonce(idAnnonce);
	 }
	 @PostMapping("/addAnnonce")
	 @ResponseBody
	 public Annonce addAnnonce (@RequestBody Annonce a)
	 {
	 Annonce annonce = annonceService.addAnnonce (a);
	 return annonce;
	 }
	 @PutMapping("/updateAnnonce")
	 @ResponseBody
	 public Annonce updateAnnonce(@RequestBody Annonce annonce) {
	 return annonceService.updateAnnonce(annonce);
	 }
	 @DeleteMapping("/deleteAnnonce/{id}")
	 @ResponseBody
	 public void deleteAnnonce(@PathVariable("id") Long idAnnonce) {
		 annonceService.deleteAnnonce(idAnnonce);
	 }

	 @GetMapping("/GetFilesByAnnonce/{id}")
	 @ResponseBody
	 public List<File> getFileByAnnonce(@PathVariable("id") Long idAnnonce) {
	 return annonceService.getFilesByAnnonce(idAnnonce);
	 }

		@GetMapping("/AfficherAnnonceByGovernorate/{gov}")
		@ResponseBody
		public List<Annonce> retrieveAnnoncesBygovernorate(@PathVariable("gov") String gov) {
		 List<Annonce> listAnnonce = annonceService.retrieveAnnoncesBygovernorate(gov);
		 return listAnnonce;
		 }
		//Achat
		@PutMapping("/BuyHome/{buy}")
	    @ResponseBody
		 public void buyHome(@RequestBody Annonce annonce,@PathVariable("buy") String buy) {
		 annonceService.buy(annonce,buy);
		 }
		//Location(vacances ou temporaire)
				@PutMapping("/leaseHome/{lease}/{leaseType}")
			    @ResponseBody
		       public Annonce leaseHome(@RequestBody Annonce annonce,@PathVariable("lease") String lease,@PathVariable("leaseType") String leaseType) {
				 return annonceService.lease(annonce,lease,leaseType);
				 }
				
				//affichage par prix decroissant
				@GetMapping("/AfficherAnnonceByprixDecroissant")
				@ResponseBody
				public List<Annonce> afficherAnnonceBypriceDecroissant() {
				 List<Annonce> listAnnonce = annonceService.afficherAnnonceBypriceDecroissant();
				 return listAnnonce;
				 }
				//affichage par prix croissant
				@GetMapping("/AfficherAnnonceByprixCroissant")
				@ResponseBody
				public List<Annonce> afficherAnnonceBypriceCroissant() {
				 List<Annonce> listAnnonce = annonceService.afficherAnnonceBypriceCroissant();
				 return listAnnonce;
				 }
				
}