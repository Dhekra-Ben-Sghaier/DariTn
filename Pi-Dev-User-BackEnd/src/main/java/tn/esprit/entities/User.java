package tn.esprit.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.esprit.validation.ValidPassword;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(	name = "users") 
public class User implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Size(min=3)
	private String username;
	@NotEmpty
	@Size(min=3)
	private String lastName;
	@Column(unique = true)
	@NotEmpty
	@Email(regexp = ".+@.+\\..+" ,message="Please provide a valid email address")
	private String email;
	@NotEmpty
	@Size(min=8)
	private String phoneNumber;
	@NotEmpty
	@Size(min=6)
	@ValidPassword
	private String password;
	
	//private boolean actived;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( 
        name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id"))
	@JsonIgnore 
	private Set<Role> roles;
	
	
	

}
