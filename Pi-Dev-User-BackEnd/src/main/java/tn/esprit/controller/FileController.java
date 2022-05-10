package tn.esprit.controller;

import java.io.IOException;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.entities.File;
import tn.esprit.services.IFileService;



@CrossOrigin(origins ="http://localhost:4200")
@Controller
public class FileController {
	
	@Autowired
	IFileService fileService;
	
	
	//@PostMapping("/addFile/{id}")
	@RequestMapping(value = "/addFile/{id}", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseBody
    public void saveProduct(@RequestParam("file") MultipartFile file,
    		@PathVariable("id") Long idAnnonce) throws IOException
    		
    {
    	
    	fileService.addFile(idAnnonce, file);
		
    	
    }
	 @PostMapping("/addFile")
	 @ResponseBody
	 public File addFile (@RequestBody File f)
	 {
	 File file = fileService.addFile (f);
	 return file;
	 }
	@GetMapping("/retrieveFile/{id}")
	@ResponseBody
	public List<File> retrieveFile(@PathVariable("id") Long idAnnonce) {
		List<File> listFile =fileService.retrieveAllFiles(idAnnonce);
	 return listFile;
	 }
	 @DeleteMapping("/deleteFile/{id}")
	 @ResponseBody
	 public void deleteFile(@PathVariable("id") Long idFile) {
		 fileService.deleteFile(idFile);
	 }
	 @GetMapping("/ShowFiles")
		@ResponseBody
		public List<File> showFiles() {
			List<File> listFile =fileService.ShowAllFiles();
		 return listFile;
		 }
	 
	 @GetMapping("/retrieveAllFiles/{pageNo}/{pageSize}")
	 @ResponseBody
	 public List<File> afficherAllFiles(@PathVariable int pageNo,@PathVariable int pageSize)
	 {
		 return fileService.afficherAllFiles(pageNo, pageSize);
	 }
}
