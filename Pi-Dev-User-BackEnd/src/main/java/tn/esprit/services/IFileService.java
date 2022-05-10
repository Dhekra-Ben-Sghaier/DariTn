package tn.esprit.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.entities.File;



public interface IFileService {
	
      void addFile (Long idAnnonce, MultipartFile file) throws IOException;
      List<File> retrieveAllFiles(Long idAnnonce);
  	  void deleteFile(Long id);
  	  List<File> ShowAllFiles();
  	  File addFile (File f);
  	  List<File> afficherAllFiles(int pageNo ,int pageSize);
}
