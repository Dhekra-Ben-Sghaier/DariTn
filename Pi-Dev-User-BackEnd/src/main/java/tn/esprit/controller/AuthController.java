package tn.esprit.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import tn.esprit.ResetPwd.ResetPassword;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import net.bytebuddy.utility.RandomString;
import tn.esprit.ResetPwd.ForgotPasswordRequest;
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
import tn.esprit.services.UserServiceImpl;
import tn.esprit.ResetPwd.ResetPassword;



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
	  
	  @Autowired
	    private JavaMailSender mailSender;

	    
	    @Autowired
	    private UserServiceImpl utilisateurService;
	  
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
		
		String link = "http://localhost:8084/SpringPiDariTN/api/auth/confirm?token=" + token;
        emailSender.send(
        		signUpRequest.getEmail(),
                buildEmail(signUpRequest.getUsername(), link));
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return regService.confirmToken(token);
    }
	
	private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
	
	 @PostMapping("/forgotPassword")
	    public ResponseEntity<String> processForgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) throws UnsupportedEncodingException, MessagingException{
	        String email = forgotPasswordRequest.getEmail();
	        String token = RandomString.make(30).replaceAll("0","o");

	      
	            utilisateurService.updateResetPasswordToken(token, email);
	           // String resetPasswordLink = Utility.getSiteURL(request) + "/forgotpssd/reset_password?token=" + token;
	            
	            //String resetPasswordLink = "http://127.0.0.1:8084/SpringPiDariTN/api/auth" + "/reset_password?token=" + token;
	            String resetPasswordLink = "http://localhost:4200/resetPwd";
	            sendEmail(email, resetPasswordLink,token);
	             return new ResponseEntity<>("reset password email has been sent successfully", HttpStatus.OK);
	       
	     
	    }
	 
	 public void sendEmail(String recipientEmail, String link, String token) throws MessagingException, UnsupportedEncodingException {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        helper.setFrom("support@Dari.tn", "Dari support");
	        helper.setTo(recipientEmail);

	        String subject = "Here's the link to reset your password";

	        String content = "<p>Hello,</p>"
	                + "<p>You have requested to reset your password.</p>"
	                + "<p>Click the link below to change your password:</p>"
	                + "<p><a href=\"" + link + "\">Change my password</a></p>"
	                + "<p>The reset code is => :"  + token +"</p>"
	                 + "<p>Ps! Use the code to reset your password</p>"
	                + "<br>"
	                + "<p>Ignore this email if you do remember your password, "
	                + "or you have not made the request.</p>";

	        helper.setSubject(subject);

	        helper.setText(content, true);

	        mailSender.send(message);


	    }
	 @GetMapping("/reset_password")
	    public ResponseEntity<tn.esprit.ResetPwd.ResetPassword> showResetPasswordForm(@Param(value = "token") String token) {
		 System.out.print("utilisateur herrrreeeee");   
		 User utilisateur = utilisateurService.getByResetPasswordToken(token);
	        System.out.print("utilisateur herrrreeeee");
	        System.out.print(utilisateur);
	        ResetPassword resetPassword = new ResetPassword();
	        if (utilisateur == null) { 
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	        }
	        resetPassword.setToken(token);
	        System.out.print(token);
	        return new ResponseEntity<>(resetPassword,HttpStatus.OK);
	    }
	    @PostMapping("/resetPasswordg")
	    public ResponseEntity<String> processResetPassword(@RequestBody ResetPassword resetPassword) {
	    	//System.out.print("nooo mazel"+ email); 
			/*
			 * ResetPassword resetPassword = new ResetPassword();
			 * resetPassword.setEmail(email); resetPassword.setPassword(password);
			 * resetPassword.setToken(token);
			 */
	        //String token = request.getParameter("token");
	        //String password = request.getParameter("password");
	        String pwd = resetPassword.getPassword();
	        User utilisateur = userRepository.findByEmail(resetPassword.getEmail()).get(0);
	       // User utilisateur = utilisateurService.getByResetPasswordToken(resetPassword.getToken());
	        
	        if (utilisateur == null) {
	           // model.addAttribute("message", "Invalid Token");
	        	System.out.print("nooo mazel"); 
	            return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);

	        } else {
	            utilisateurService.updatePassword(utilisateur, pwd);

	          //  model.addAttribute("message", "You have successfully changed your password.");
	            return new ResponseEntity<>("password updated successfully ",HttpStatus.OK);

	        }

	     

	    }

}
