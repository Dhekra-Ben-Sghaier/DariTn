package tn.esprit.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.entities.Annonce;
import tn.esprit.entities.File;
import tn.esprit.repository.AnnonceRepository;
import tn.esprit.repository.FileRepository;



@Service
public class FileServiceImpl implements IFileService {

	@Autowired
	FileRepository fileRepository;
	@Autowired
	AnnonceRepository annonceRepository;
	@Override
	public void addFile(Long idAnnonce,MultipartFile file) throws IOException {
		
		Annonce a = annonceRepository.findById(idAnnonce).orElse(null);
		File f = new File();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			f.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			f.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			f.setAnnonce(a);
	
			fileRepository.save(f);
	}
	
	@Override
	public void deleteFile(Long id) {
		File f =fileRepository.findById(id).orElse(null); 
		fileRepository.delete(f);
		
	}

	@Override
	public List<File> retrieveAllFiles(Long idAnnonce) {
		Annonce a= annonceRepository.findById(idAnnonce).orElse(null);
		return a.getFiles();
	}

	@Override
	public List<File> ShowAllFiles() {
		return (List<File>)fileRepository.findAll();
		
	}

	@Override
	public File addFile(File f) {
		return fileRepository.save(f);
	}

	@Override
	public List<File> afficherAllFiles(int pageNo, int pageSize) {
		PageRequest paging = PageRequest.of(pageNo, pageSize);
		Page<File> pageResult = fileRepository.findAll(paging);
		return pageResult.toList();
	}
	

}
