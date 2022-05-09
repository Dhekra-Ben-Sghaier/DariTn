package tn.esprit.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.security.jwt.ConfiramtionToken;

@Service
public class RegService {
	
	@Autowired
	UserDetailsServiceImpl userService;
   EmailValidator emailValidator;
	@Autowired
    ConfirmationTokenService confirmationTokenService;
    //EmailSender emailSender;

	@Transactional
    public String confirmToken(String token) {
        ConfiramtionToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getComfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
	
}
