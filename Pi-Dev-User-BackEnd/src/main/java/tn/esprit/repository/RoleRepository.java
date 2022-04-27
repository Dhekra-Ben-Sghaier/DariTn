package tn.esprit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Role;
import tn.esprit.entities.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	//Role findByRole(String role);	
	Optional<Role> findByRole(RoleName name);
}
