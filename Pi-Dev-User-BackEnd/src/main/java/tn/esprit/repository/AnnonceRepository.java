package tn.esprit.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.entities.Annonce;






@Repository
@Transactional
public interface AnnonceRepository  extends JpaRepository<Annonce, Long>{

	@Query("Select a from Annonce a where a.governorate like ?1 ")
	List<Annonce> afficherAnnonceBygovernorate(String governorate );
	
	@Query("Select a from Annonce a order by a.price DESC")
	List<Annonce> afficherAnnonceBypriceDecroissant();
	
	@Query("Select a from Annonce a order by a.price ASC")
	List<Annonce> afficherAnnonceBypriceCroissant();
	
	@Modifying(clearAutomatically= true)
	@Query("update Annonce a set a.closureDate=?1, a.transaction=?2 where a.idAnnonce=?3")
	void modifierAnnonceBybuy(Date cd ,String tr, Long id);
	
	
}
