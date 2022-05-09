package tn.esprit.ResetPwd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import tn.esprit.entities.User;
import tn.esprit.repository.UserRepository;

public class ResetPasswordService {

	@Autowired
	UserRepository userRepository;
	public void updateResetPasswordToken(String token, String email)   {
        User utilisateur = userRepository.findUserByEmail(email).orElse(null);
        utilisateur.setResetPasswordToken(token);
        userRepository.save(utilisateur);
        
    }
    public User getByResetPasswordToken(String token) {
        return userRepository.getUtilisateurByResetPasswordToken(token);
    }
    public void updatePassword(User utilisateur, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        utilisateur.setPassword(encodedPassword);

        utilisateur.setResetPasswordToken(null);
        userRepository.save(utilisateur);
    }
}
