package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

import tn.esprit.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/*public User findByUsername(String username);
	//public List<User> findByMail(String mail);*/
	/* @Query("select u from User u where u.email = ?1")
	  User findByEmailAddress(String mail);
	 //User findByUserMAil(String userName);*/
	
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Optional<User> findUserByEmail(String email);
	List<User> findByEmail(String email);
	@Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.enabled = TRUE WHERE u.email = ?1")
    int enableAppUser(String email);
	
	User getUtilisateurByResetPasswordToken(String reset);
	 
}
