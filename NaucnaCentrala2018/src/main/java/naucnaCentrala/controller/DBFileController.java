package naucnaCentrala.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import naucnaCentrala.model.DBFile;
import naucnaCentrala.response.UploadFileResponse;
import naucnaCentrala.service.DBFileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/dbfile")
public class DBFileController {
	
	
	@Autowired
	private DBFileService dbFileService;
	
	
	@PostMapping(value="/uploadfile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		
		DBFile dbfile = dbFileService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/dbfile/downloadFile=")
				.path(Long.toString(dbfile.getId()))
				.toUriString();
				
		return new UploadFileResponse(dbfile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
				
	}
	
	
	
	@GetMapping(value="/downloadFile={fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId){
		System.out.println("POGODIO BACKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		
		DBFile dbFile = dbFileService.getFile(fileId);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
		
		
	}

}
