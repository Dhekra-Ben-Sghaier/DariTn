package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Annonce;
import tn.esprit.entities.File;

public interface IAnnonceService {

	List<Annonce> retrieveAllAnnonces();
	void deleteAnnonce(Long id);
	Annonce retrieveAnnonce(Long id);
	List<File> getFilesByAnnonce(Long idAnnonce);
	Annonce addAnnonce (Annonce a);
	Annonce updateAnnonce (Annonce a);
	List<Annonce> retrieveAnnoncesBygovernorate(String governorate );
	void buy (Annonce a,String transaction);
	Annonce lease (Annonce a,String transaction,String locationType);
	List<Annonce> afficherAnnonceBypriceDecroissant();
	List<Annonce> afficherAnnonceBypriceCroissant();

}
