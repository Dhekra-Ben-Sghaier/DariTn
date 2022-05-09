package tn.esprit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    
    private Long cardNumber;
    private int cvc;
    private int expirationMonth;
    private int expirationYear;
    private String userEmail;
    private String userName;
    private float cost;
    private long idFurniture;
} 
