package tn.esprit.services;

import java.util.List;
import java.util.Set;

import tn.esprit.dto.PanierDTO;
import tn.esprit.entities.Furniture;
import tn.esprit.entities.Panier;
import tn.esprit.repository.PanierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanierService {
    @Autowired
    PanierRepository panierRepository;

    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    } 

    public Panier save(PanierDTO panierDTO) {
        Panier panier = Panier.builder()
                .totalPanier(panierDTO.getTotalPanier())
                .userID(panierDTO.getUserID())
                .furnitures(panierDTO.getFurnitures()).build();

        return panierRepository.save(panier);
    }

    public Panier updatePanier(Panier panier) {

        return panierRepository.save(panier);

    }

    public List<Panier> retrieveAllPanier() {
        return panierRepository.findAll();
    }

    public Panier retrievePanierById(Long panierId) {
        return panierRepository.findById(panierId).get();
    }

    public void deletePanier(Long panierId) {
        panierRepository.deleteById(panierId);
    }

    public Set<Furniture> retrieveFurnitureFromPanier(Long panierId) {
        Panier panier = panierRepository.getById(panierId);
        return panier.getFurnitures();
    }

    public Panier retrievePanierByUserId(Long userId){
        return panierRepository.findByUserID(userId).orElseThrow(() -> new RuntimeException("Cannot find panier with user id"+userId));
    }

    public void deleteFurnitureFromPanier(Long idFurniture, Long panierId) {
        Panier panier = panierRepository.getById(panierId);
       // Set<Furniture> furnitureList = panier.getFurnitures();

    }
}
