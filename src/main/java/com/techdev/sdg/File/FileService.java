package com.techdev.sdg.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    public File constructFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if (fileName.contains("..")) {
            throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
        }

        File f = new File(fileName, file.getContentType(), file.getBytes());

        return f;
    }

    public File getFile(Long fileId) throws Exception {
        return repository.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id " + fileId));
    }

    public void deleteFiles(List<File> files) {
        while(files.remove(null));
        repository.deleteAll(files);
    }
}
