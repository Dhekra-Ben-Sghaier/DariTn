package tn.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.User;
import tn.esprit.services.UserServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	

    	
    	
    
    
    
    @DeleteMapping("/admin/deleteUser/")
	@ResponseBody
	void supprimerStock(@RequestParam Long idUser) {
		userService.deleteUser(idUser);
		
	}

    @GetMapping("/listUsers")
	List<User> showUsers(){
		return userService.getallUsers();
		
	}
    
    @GetMapping("/Mng/showUser/")
	@ResponseBody
	User showUser(@RequestParam Long idUser) {
		return userService.getUserbyId(idUser);
		
	}
    
    @PutMapping("/Mng/updateUser")
	@ResponseBody
	User updateUser(@RequestBody User u) {
		return userService.updateUser(u);
	}
}
