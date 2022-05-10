package tn.esprit.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.File;




@Repository
public interface FileRepository extends JpaRepository<File, Long> {



}
