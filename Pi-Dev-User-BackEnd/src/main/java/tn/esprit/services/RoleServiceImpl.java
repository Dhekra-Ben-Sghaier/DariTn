package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.entities.Role;
import tn.esprit.repository.RoleRepository;

public class RoleServiceImpl implements IRoleService{

	@Autowired
	RoleRepository roleRepository;
	
	
	@Override
	public List<Role> retriveRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role retriveRoleById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role saveUser(Role r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role updateUser(Role r) {
		// TODO Auto-generated method stub
		return null;
	}

}
