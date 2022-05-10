package tn.esprit.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.email.EmailSender;
import tn.esprit.entities.Role;
import tn.esprit.entities.RoleName;
import tn.esprit.entities.User;
import tn.esprit.payload.request.LoginRequest;
import tn.esprit.payload.request.SignupRequest;
import tn.esprit.payload.response.JwtResponse;
import tn.esprit.payload.response.MessageResponse;
import tn.esprit.repository.RoleRepository;
import tn.esprit.repository.UserRepository;
import tn.esprit.security.jwt.ConfiramtionToken;
import tn.esprit.security.jwt.JwtUtils;
import tn.esprit.services.ConfirmationTokenService;
import tn.esprit.services.RegService;
import tn.esprit.services.UserDetailsImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	  @Autowired
	  ConfirmationTokenService confirmationTokenService;
	  
	  @Autowired
	  RegService regService;
	  
	  @Autowired
	  EmailSender emailSender;
	  
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		  
		 
		  System.out.println("Emaiiiil =>>");
		    System.out.println(loginRequest.getEmail());
		    String mail = loginRequest.getEmail();
		   loginRequest.setUsername(userRepository.findByEmail(mail).get(0).getUsername());

		    System.out.println(loginRequest.getUsername());
		  Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(), 
	                         userDetails.getUsername(), 
	                         userDetails.getEmail(), 
	                         roles));
	  }
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		/*if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}*/
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		User user = new User( );
		user.setUsername(signUpRequest.getUsername()); 
		user.setLastName(signUpRequest.getLastname());
		 user.setEmail(signUpRequest.getEmail());
		 user.setPassword(encoder.encode(signUpRequest.getPassword()));
		 user.setPhoneNumber(signUpRequest.getPhoneNumber());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByRole(RoleName.CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			
				Role adminRole = roleRepository.findByRole(RoleName.ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);
			
		}
		user.setRoles(roles);
		userRepository.save(user);
		String token = UUID.randomUUID().toString();
		ConfiramtionToken confirmationToken = new ConfiramtionToken(token,LocalDateTime.now(),LocalDateTime.now().plusMinutes(60),user);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		String link = "http://localhost:8084/api/auth/signup/confirm?token=" + token;
     
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return regService.confirmToken(token);
    }
	
}
