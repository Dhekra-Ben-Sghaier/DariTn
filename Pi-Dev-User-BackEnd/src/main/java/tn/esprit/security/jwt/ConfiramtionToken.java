package tn.esprit.security.jwt;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.entities.Role;
import tn.esprit.entities.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ConfiramtionToken {

	@SequenceGenerator(name= "confirmation_token_sequence",
								sequenceName = "confirmation_token_sequence",
								allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "confirmation_token_sequence") 
	private Long tokenId;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private LocalDateTime expiresAt;
	private LocalDateTime comfirmedAt;
	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "id")
	private User user;
	public ConfiramtionToken(String token, LocalDateTime expiresAt,
			 User user) {
		 
		this.token = token;
		this.expiresAt = expiresAt;
		this.user = user;
	}
	
	public ConfiramtionToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,
			 User user) {
		 
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.user = user;
	}


	

}
