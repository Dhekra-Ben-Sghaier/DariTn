package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Role;

public interface IRoleService {
	List<Role> retriveRoles();
	Role retriveRoleById(Long id);
	Role saveUser (Role r);
	
	Role updateUser (Role r);

}
