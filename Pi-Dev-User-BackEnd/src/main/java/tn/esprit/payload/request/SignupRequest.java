package tn.esprit.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(min = 3, max = 20)
  private String lastname;
  
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
	@NotEmpty
	@Size(min=8)
	private String phoneNumber;
  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  

  
}