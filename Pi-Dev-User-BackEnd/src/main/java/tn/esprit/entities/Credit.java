package com.example.credit.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	@Column(name="nom_client")
	 String nomClient;
	@Column(name="prenom_client")
	 String prenomClient;
	@Column(name="salaire_client")
	 float salaireClient;
	@Column(name="nbr_enfant")
	 Integer nbrEnfant;
	@Column(name="email_client")
	 String emailClient;
	
	


	
	
	
	
}

