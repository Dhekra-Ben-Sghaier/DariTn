package tn.esprit.controller;

import java.io.IOException;
import java.util.List;

import tn.esprit.dto.FurnitureDTO;
import tn.esprit.entities.Furniture;
import tn.esprit.services.FurnitureService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins =  "http://localhost:4200")
@RestController
@RequestMapping("/api/furniture")
public class FurnitureController {

    private FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @PostMapping("/create-new-furniture")
    public ResponseEntity<Furniture> createNewFurniture(@ModelAttribute  FurnitureDTO furnitureDTO) throws IOException, Exception {

        try {
            Furniture furniture = furnitureService.save(furnitureDTO);
            return new ResponseEntity<>(furniture, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-all-furnitures")
    public List<Furniture> getAllFurnitures() {

        return furnitureService.retrieveAllFurniture();
    }

    @GetMapping("/get-furniture-by-id/{furniture-id}")
    @ResponseBody
    public Furniture getFurnitureById(@PathVariable("furniture-id") Long furnitureId) {
        return furnitureService.retrieveFurnitureById(furnitureId);
    }

    @DeleteMapping("/delete-furniture/{furniture-id}")
    public ResponseEntity<String> deleteFurnitureById(@PathVariable("furniture-id") Long furnitureId) {

        if (furnitureService.retrieveFurnitureById(furnitureId) == null) {
            return new ResponseEntity<>("The furniture was not found", HttpStatus.NOT_FOUND);

        }

        furnitureService.deleteFurniture(furnitureId);
        return new ResponseEntity<>("The furniture was succesfully deleted", HttpStatus.OK);

    }

    @PutMapping("/update-furniture")
    public ResponseEntity<Furniture> updateFurniture(@RequestBody Furniture furniture) {

        return new ResponseEntity<>(furnitureService.updateFurniture(furniture), HttpStatus.OK);

    }
}
