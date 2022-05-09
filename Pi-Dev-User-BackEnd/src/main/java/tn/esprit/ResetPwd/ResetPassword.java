package tn.esprit.ResetPwd;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.esprit.entities.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResetPassword {

	
	private String password;
    private String token;
}
