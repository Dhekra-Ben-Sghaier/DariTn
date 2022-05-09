package tn.esprit.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPanier;
    private float totalPanier;
    
    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime creationDate;

    @Column(updatable = false)
    private long userID;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Furniture> furnitures;
}
