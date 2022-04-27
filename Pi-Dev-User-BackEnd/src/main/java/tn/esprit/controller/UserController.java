package tn.esprit.controller;

import java.util.List;

/*import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.entities.User;
import tn.esprit.services.IUserService;*/

/*
@CrossOrigin(origins= "*")
@RestController
@Api(tags = "user management")
@RequestMapping("/UserMng")*/
public class UserController {
	
	/*@Autowired
	IUserService userService;
	
    @PostMapping("/register")
    @ResponseBody
    public void userRegistration(@Valid @RequestBody User user){
    	
    	String msg="";
    	User userExists = userService.findUserByMail(user.getEmail());
    	if (userExists != null) {
    		msg="There is already a user registered with the user name provided";
    		} else {
    			userService.addUser(user);
    			msg="OK";
	    		System.out.print(msg); 
    		}
    		 }
    	
    	
    
    
    
    @DeleteMapping("/admin/deleteUser/{id}")
	@ResponseBody
	void supprimerStock(Long idUser) {
		userService.deleteUser(idUser);
		
	}

    @GetMapping("/admin/listUsers")
	List<User> showUsers(){
		return userService.retriveUsers();
		
	}
    
    @GetMapping("/Mng/showUser/{id}")
	@ResponseBody
	User showUser(Long idUser) {
		return userService.retriveUserById(idUser);
		
	}
    
    @PutMapping("/Mng/updateUser")
	@ResponseBody
	User updateUser(@RequestBody User u) {
		return userService.updateUser(u);
	}*/
}
