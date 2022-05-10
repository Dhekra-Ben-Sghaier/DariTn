package tn.esprit.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Annonce;
import tn.esprit.entities.File;
import tn.esprit.repository.AnnonceRepository;
import tn.esprit.repository.FileRepository;

@Service
public class AnnonceServiceImpl implements IAnnonceService{

	@Autowired
    AnnonceRepository annonceRepository;
	@Autowired
    FileRepository fileRepository;
	@Override
	public List<Annonce> retrieveAllAnnonces() {
		return (List<Annonce>)annonceRepository.findAll();
	}

	@Override
	public void deleteAnnonce(Long id) {
		Annonce a =annonceRepository.findById(id).orElse(null); 
		annonceRepository.delete(a);
		
	}

	@Override
	public Annonce retrieveAnnonce(Long id) {
		return annonceRepository.findById(id).orElse(null);
	}

	

	@Override
	public Annonce addAnnonce(Annonce a) {
		Date d= new Date();
		a.setDateDepot(d);
		return annonceRepository.save(a);
	}

	@Override
	public Annonce updateAnnonce(Annonce a) {
		return annonceRepository.save(a);
	}

	@Override
	public List<File> getFilesByAnnonce(Long idAnnonce) {
		Annonce a=annonceRepository.findById(idAnnonce).orElse(null);
		return (List<File>)a.getFiles();
	}

	@Override
	public List<Annonce> retrieveAnnoncesBygovernorate(String governorate ) {
		
		return annonceRepository.afficherAnnonceBygovernorate( governorate );
	}

	@Override
	public void buy(Annonce a, String transaction) {
		Date d= new Date();
		a.setClosureDate(d);
		a.setTransaction(transaction);
		annonceRepository.modifierAnnonceBybuy(d,transaction , a.getIdAnnonce());
		

	}

	@Override
	public Annonce lease(Annonce a, String transaction, String locationType) {
		Date d= new Date();
		a.setClosureDate(d);
		a.setTransaction(transaction);
		a.setLocationType(locationType);
		annonceRepository.save(a);
		return a;
	}

	@Override
	public List<Annonce> afficherAnnonceBypriceDecroissant() {
		
		return annonceRepository.afficherAnnonceBypriceDecroissant();
	}

	@Override
	public List<Annonce> afficherAnnonceBypriceCroissant() {
		return annonceRepository.afficherAnnonceBypriceCroissant();
	}

	

}
