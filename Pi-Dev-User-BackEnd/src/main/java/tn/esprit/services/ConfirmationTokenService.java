package tn.esprit.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.esprit.repository.ConfirmationTokenRepository;
import tn.esprit.security.jwt.ConfiramtionToken;

@Service
public class ConfirmationTokenService {

	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	
	
	public void saveConfirmationToken(ConfiramtionToken token) {
		confirmationTokenRepository.save(token);
	}
	
	public Optional<ConfiramtionToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
	
	
	
}
