package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.User;

public interface IUserService {
	List<User> retriveUsers();
	User retriveUserById(Long id);
	User saveUser (User f);
	
	User updateUser (User f);
	
	void deleteUser(Long id);
	boolean checkIfUserExist(String email);
	void register(User user);
	User addUser(User user);
	void addRoleToUser(User user, String rolename);
	User findUserByName(String username);
	User findUserByMail(String username);
	

}
