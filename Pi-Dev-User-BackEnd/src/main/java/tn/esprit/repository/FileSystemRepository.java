package tn.esprit.repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class FileSystemRepository {

    String RESOURCES_DIR = Path.of("").toAbsolutePath().toString() + "/productimages/";

    public String save(byte[] content, String imageName) throws Exception {
        String uniqueImagesName = new Date().getTime() + "-" + imageName;
        Path newFile = Paths.get(RESOURCES_DIR + uniqueImagesName);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        // return newFile.toAbsolutePath()
        // .toString();

        return uniqueImagesName;
    }
}