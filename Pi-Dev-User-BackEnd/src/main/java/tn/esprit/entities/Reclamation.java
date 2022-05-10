package com.example.credit.entities;

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
public class Reclamation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	@Column(name="titre_reclamation")
	 String titreReclamation;
	@Column(name="objet_reclamation")
	 String objet_Reclamation;
	@Column(name="adresse_client")
	 String adresseClient;
	@Column(name="email_client")
	 String emailClient;
	@Column(name="numero_client")
	 Integer numeroClient;
	
}
