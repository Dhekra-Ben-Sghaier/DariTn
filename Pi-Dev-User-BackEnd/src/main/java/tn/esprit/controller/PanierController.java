package tn.esprit.controller;

import java.util.List;
import java.util.Set;

import tn.esprit.dto.PanierDTO;
import tn.esprit.entities.Furniture;
import tn.esprit.entities.Panier;
import tn.esprit.repository.PanierRepository;
import tn.esprit.services.FurnitureService;
import tn.esprit.services.PanierService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins =  "http://localhost:4200")
@RestController
@RequestMapping("/api/panier")
public class PanierController {

    private PanierService panierService;

    private FurnitureService furnitureService;

    private PanierRepository panierRepository;

    public PanierController(PanierService panierService, FurnitureService furnitureService,PanierRepository panierRepository) {
        this.panierService = panierService;
        this.furnitureService = furnitureService;
        this.panierRepository = panierRepository;
    }

    @PostMapping("/create-new-basket")
    @ResponseBody
    public ResponseEntity<Panier> createNewPanier(@RequestBody PanierDTO panierDTO) {

        long userId = 1;

        try {
               Panier panier = panierService.save(panierDTO);
                //panier.setTotalPanier(panier.getFurnitures().);
                return new ResponseEntity<>(panier, HttpStatus.CREATED);

        } catch (RuntimeException ex) {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add-furniture-to-cart")
    public ResponseEntity<Panier> addFurnitureToCart(@RequestBody Furniture furnitur){

        Furniture furniture = furnitureService.retrieveFurnitureById(furnitur.getIdFurniture());

        if(furniture == null) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        
        Panier panier = panierService.retrievePanierByUserId(Long.valueOf(1));

        
        Set<Furniture> panierlist = panier.getFurnitures();
        panierlist.add(furniture);

        return new ResponseEntity<Panier>(panierRepository.save(panier),HttpStatus.OK);
    }

    @GetMapping("/get-all-paniers")
    public List<Panier> getAllPaniers() {

        return panierService.retrieveAllPanier();
    }

    @GetMapping("/get-panier-by-id/{panier-id}")
    @ResponseBody
    public Panier getPanierById(@PathVariable("panier-id") Long panierId) {
        return panierService.retrievePanierById(panierId);
    }

    @GetMapping("/get-furniture-panier/{panier-id}")
    @ResponseBody
    public Set<Furniture> getFurniturePanier(@PathVariable("panier-id") Long panierId) {
        return panierService.retrieveFurnitureFromPanier(panierId);
    }

    @DeleteMapping("/delete-panier/{panier-id}")
    public ResponseEntity<String> deletePanierById(@PathVariable("panier-id") Long panierId) {

        if (panierService.retrievePanierById(panierId) == null) {
            return new ResponseEntity<>("The Panier was not found", HttpStatus.NOT_FOUND);

        }

        panierService.deletePanier(panierId);
        return new ResponseEntity<>("The Panier was succesfully deleted", HttpStatus.OK);

    }

    @DeleteMapping("/panier/delete-furniture-panier/{panier-id}/{furniture-id}")
    public ResponseEntity<String> deleteFurnitureFromPanier(@PathVariable("panier-id") Long panierId, @PathVariable("furniture-id") Long furnitureId) {
        Panier panier = panierService.retrievePanierById(panierId);
        Set<Furniture> furnitureList = panier.getFurnitures();
        
        return new ResponseEntity<>("The Furniture was succesfully deleted from basket", HttpStatus.OK);
    }

    @PutMapping("/update-panier")
    public ResponseEntity<Panier> updatePanier(@RequestBody Panier panier) {

        return new ResponseEntity<>(panierService.updatePanier(panier), HttpStatus.OK);

    }
}
