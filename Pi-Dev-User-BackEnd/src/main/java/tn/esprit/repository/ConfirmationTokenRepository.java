package tn.esprit.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.security.jwt.ConfiramtionToken;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfiramtionToken, Long>{

	Optional<ConfiramtionToken> findByToken(String token);
	
	 @Transactional
	    @Modifying
	    @Query("UPDATE ConfiramtionToken c " +
	            "SET c.comfirmedAt = ?2 " +
	            "WHERE c.token = ?1")
	    int updateConfirmedAt(String token, LocalDateTime comfirmedAt);
}
