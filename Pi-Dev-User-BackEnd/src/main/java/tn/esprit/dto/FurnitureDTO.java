package tn.esprit.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FurnitureDTO {

    private BigDecimal price;

    private String description;

    private double length;

    private double width;

    private FurnitureType furnitureType;

    private MultipartFile image;

    private String furnitureTitle;

}
