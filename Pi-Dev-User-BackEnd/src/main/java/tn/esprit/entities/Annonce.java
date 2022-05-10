package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@Entity
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Annonce implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idAnnonce;
	String title;
	String description;
	int area;
	String governorate;
	String delegation;
	String transaction;
	int nbRoom;
	int nbBathRoom;
	@Temporal(TemporalType.DATE)
	Date closureDate;
	@Temporal(TemporalType.DATE)
	Date dateDepot;
	/*String state;
	 */
	 
	int price;
	String locationType;
    private Long userId;
    
    
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="annonce")
	@JsonBackReference 
	List<File> files;
}
