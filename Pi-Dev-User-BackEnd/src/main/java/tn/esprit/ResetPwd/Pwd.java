package tn.esprit.ResetPwd;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.entities.User;
import tn.esprit.repository.ConfirmationTokenRepository;
import tn.esprit.repository.UserRepository;
import tn.esprit.security.jwt.ConfiramtionToken;

@Service
public class Pwd {
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	UserRepository userRepository;
	
	 private String generateVerificationToken(User user) {
	        String token = UUID.randomUUID().toString();
	        ConfiramtionToken verificationToken = new ConfiramtionToken();
	        verificationToken.setToken(token);
	        verificationToken.setUser(user);
	        confirmationTokenRepository.save(verificationToken);

	        return token;
	    }
	    public void verifyAccount(String token) {
	        Optional<ConfiramtionToken> verificationToken = confirmationTokenRepository.findByToken(token);
	        //verificationToken.orElseThrow(()-> new DariException("Invalid Token"));
	        fetchUserAndEnable(verificationToken.get());
	    }
	    private void fetchUserAndEnable(ConfiramtionToken verificationToken) {
	        Long userId = verificationToken.getUser().getId();
	        User user = userRepository.findById(userId).orElse(null);
	               
	        user.setEnabled(true);
	        userRepository.save(user);
	    }

}
