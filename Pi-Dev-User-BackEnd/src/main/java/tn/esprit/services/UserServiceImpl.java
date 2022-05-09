package tn.esprit.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.Role;
import tn.esprit.entities.User;
import tn.esprit.repository.RoleRepository;
import tn.esprit.repository.UserRepository;

@Slf4j
@Service
public class UserServiceImpl {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	UserRepository usrRepository;
	PasswordEncoder passwordEncoder;
	
	
	
	
	 public void updateResetPasswordToken(String token, String email) {
	        User user = userRepository.findUserByEmail(email).orElse(null);
	        
	            user.setResetPasswordToken(token);
	            userRepository.save(user);
	         
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
	    
	    public void deleteUser(User user){
	        userRepository.delete(user);
	    }
	    
	    public User getuserbyId(Long id) {
	        Optional<User> user = userRepository.findById(id);
	        return  user.get();
	    }

	    
	    public List<User> getallUsers() {
	        return (List<User>) userRepository.findAll();
	    }

	
}
