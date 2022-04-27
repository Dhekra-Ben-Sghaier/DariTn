package tn.esprit.services;

import java.util.List;
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

public class UserServiceImpl implements IUserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	
	
	UserRepository usrRepository;
	PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository usrRepository) {
		this.usrRepository = usrRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	
}
	
	
	/*@Override
	public void register(User user) {

        //Let's check if user already registered 
        if(!(checkIfUserExist(user.getMail()))){
        	 User userEntity = new User();
             BeanUtils.copyProperties(user, userEntity);
             encodePassword(userEntity, user);
             userRepository.save(userEntity);
        }
        else {
        	System.out.print("the email: "+user.getMail()+" is already used!");
        	//throw new Exception("user already exist");
        }
       
    }*/
	
	@Override
	public User addUser(User user) {
		return user;
		/*User usr = userRepository.findByEmailAddress(user.getMail());
		if(usr!=null) throw new RuntimeException("User already exist");
			if(!user.getPassword().equals(user.getConfirmedPassword()))throw new RuntimeException("Please confirm you password");
			User newUser= new User();
			newUser.setUsername(user.getUsername());
			newUser.setLastName(user.getLastName());
			newUser.setMail(user.getMail());
			newUser.setPhoneNumber(user.getPhoneNumber());
			newUser.setActived(true);
			newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
			userRepository.save(newUser);
			addRoleToUser(user, "CLIENT");
			return newUser;*/
		
		
	}
	
	@Override
	public void addRoleToUser(User user, String rolename) {
		
		/*Set <Role> role= (Set<Role>) roleRepository.findByRole(rolename);
		user.setRoles(role);*/
	}
	
	@Override
	public List<User> retriveUsers() {
		List<User> users = userRepository.findAll();
		for(User user: users) {
			log.info("user :" + user);
		}
		return users;
	}

	@Override
	public User retriveUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);	
		
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
		
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}
	@Override
    public boolean checkIfUserExist(String email) {
		return false;
        //return userRepository.findByEmailAddress(email) !=null ? true : false;
    }
	/*void encodePassword( User userEntity, User user){
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }*/

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User findUserByName(String username) {
		
		return findUserByName(username);
	}


	@Override
	public User findUserByMail(String mail) {
		return null;
		
		//return userRepository.findByEmailAddress(mail);
	}

}
