package tn.esprit.dto;

import java.util.Set;

import tn.esprit.entities.Furniture;

import lombok.Data;

@Data
public class PanierDTO {
    private float totalPanier;
    private Set<Furniture> furnitures;
    private long userID;
}
