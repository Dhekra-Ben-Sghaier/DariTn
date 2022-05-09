package tn.esprit.repository;

import tn.esprit.entities.Furniture;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

}
