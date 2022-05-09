package tn.esprit.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;
    private String refPayment ;
    private float cost;
   
    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    private String userName;
    
}
