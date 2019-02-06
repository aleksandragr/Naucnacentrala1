package naucnaCentrala.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import naucnaCentrala.exception.FileStorageException;
import naucnaCentrala.exception.MyFileNotFoundException;
import naucnaCentrala.model.DBFile;
import naucnaCentrala.repository.DBFileRepository;

@Service
public class DBFileService {
	
	@Autowired
	private DBFileRepository dbFileRepository;
	
	
	public DBFile storeFile(MultipartFile file) {
		
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		
		try {
			
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			DBFile dbfile = new DBFile(fileName, file.getContentType(), file.getBytes());
			
			return dbFileRepository.save(dbfile);
			
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
		
		
	}
	
	
	public DBFile getFile(Long fileId) {
		
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}

}
