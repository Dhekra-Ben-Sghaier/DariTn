package tn.esprit.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import tn.esprit.dto.FurnitureType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Furniture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFurniture;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "deactivation_status")
    @Builder.Default
    private Status deactivationStatus = Status.AVAILABLE;

    private BigDecimal price;

    @NotBlank
    private String description;

    private double length;

    private double width;
    
    @Enumerated(EnumType.STRING)
    private FurnitureType furnitureType;

    private String imageURL;

    @NotNull
    private String furnitureTitle;

    private Long userId;
}
