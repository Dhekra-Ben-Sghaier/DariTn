package tn.esprit.services;

import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;

import tn.esprit.dto.FurnitureDTO;
import tn.esprit.entities.Furniture;
import tn.esprit.repository.FileSystemRepository;
import tn.esprit.repository.FurnitureRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class FurnitureService {


    private FurnitureRepository furnitureRepository;
    private final FileSystemRepository fileSystemRepository;

    public FurnitureService(FurnitureRepository furnitureRepository,FileSystemRepository fileSystemRepository) {
        this.furnitureRepository = furnitureRepository;
        this.fileSystemRepository=fileSystemRepository;
    }

    public Furniture save(FurnitureDTO furnitureDTO) throws IOException, Exception {

        Furniture furniture = Furniture.builder()
                .price(furnitureDTO.getPrice())
                .description(furnitureDTO.getDescription())
                .width(furnitureDTO.getWidth())
                .length(furnitureDTO.getLength()).furnitureType(furnitureDTO.getFurnitureType())            
                .furnitureTitle(furnitureDTO.getFurnitureTitle())
                .userId(Long.valueOf(1)).build();
       Furniture furnitureSaved = furnitureRepository.save(furniture);
       if(furnitureDTO.getImage() != null)
       uploadFurnitureImage(furnitureSaved.getIdFurniture(), furnitureDTO.getImage());
       return furnitureSaved;
    }

 

    private void uploadFurnitureImage(Long idFurniture, MultipartFile image) throws IOException, Exception {
               // 1. Check if image is not empty
               isFileEmpty(image);

               String imageURL = "http://localhost:8089/daritn-images/"+fileSystemRepository.save(image.getBytes(), image.getOriginalFilename());
               Furniture furniture = retrieveFurnitureById(idFurniture);
               furniture.setImageURL(imageURL);
       
    }

  
    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

    public Furniture updateFurniture(Furniture furniture) {

        return furnitureRepository.save(furniture);

    }

    public List<Furniture> retrieveAllFurniture() {
        return furnitureRepository.findAll();
    }

    public Furniture retrieveFurnitureById(Long furnitureId) {
        return furnitureRepository.findById(furnitureId).orElse(null);
    }

    public void deleteFurniture(Long furnitureId) {
        furnitureRepository.deleteById(furnitureId);
    }

}
