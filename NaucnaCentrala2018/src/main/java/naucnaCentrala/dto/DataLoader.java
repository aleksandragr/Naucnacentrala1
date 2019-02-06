package naucnaCentrala.dto;

import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import naucnaCentrala.model.DBFile;
import naucnaCentrala.repository.DBFileRepository;
import naucnaCentrala.service.DBFileService;

@Component
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	private DBFileRepository dbFileRepository;
	
	@Autowired
	private DBFileService dbFileService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		
		final String fp = System.getProperty("user.dir");
		String fpp=new String(fp+"\\Magazini\\");
		
		
		
		File file = new File(fpp + "Mineraloske i kristalografske osobine zeolitskog tufa HEU-tipa lokaliteta Novakovic, Bosna i Hercegovina.pdf");
	    FileInputStream input = new FileInputStream(file);
	    MultipartFile multipartFile = new MockMultipartFile("file",
	            file.getName(), "application/pdf", IOUtils.toByteArray(input));
	     
	    DBFile db = dbFileService.storeFile(multipartFile);
		
	    
	    File file1 = new File(fpp + "Upravljacko – nadzorni sistem pogona za proizvodnju biodizela.pdf");
	    FileInputStream input1 = new FileInputStream(file1);
	    MultipartFile multipartFile1 = new MockMultipartFile("file",
	            file1.getName(), "application/pdf", IOUtils.toByteArray(input1));
	     
	    DBFile db1 = dbFileService.storeFile(multipartFile1);
	    
	    
	    File file2 = new File(fpp + "Cvrste disperzije sa karbamazepinom optimizacija formulacija, karakterizacija i ispitivanje dugorocne stabilnosti.pdf");
	    FileInputStream input2 = new FileInputStream(file2);
	    MultipartFile multipartFile2 = new MockMultipartFile("file",
	            file2.getName(), "application/pdf", IOUtils.toByteArray(input2));
	     
	    DBFile db2 = dbFileService.storeFile(multipartFile2);
	    
	    
	    File file3 = new File(fpp + "Hibridni metod za detekciju ivica na TEM slikama nanocestica.pdf");
	    FileInputStream input3 = new FileInputStream(file3);
	    MultipartFile multipartFile3 = new MockMultipartFile("file",
	            file3.getName(), "application/pdf", IOUtils.toByteArray(input3));
	     
	    DBFile db3 = dbFileService.storeFile(multipartFile3);
	    
	    
	    File file4 = new File(fpp + "Ispitivanje uticaja tretmana i mineraloskog sastava na tacku nultog naelektrisanja crvenog mulja.pdf");
	    FileInputStream input4 = new FileInputStream(file4);
	    MultipartFile multipartFile4 = new MockMultipartFile("file",
	            file4.getName(), "application/pdf", IOUtils.toByteArray(input4));
	     
	    DBFile db4 = dbFileService.storeFile(multipartFile4);
	    
	    
	    File file5 = new File(fpp + "Uticaj dodatka nanopunila na svojstva silikonskih materijala na osnovu razlicitih prekursora mreza.pdf");
	    FileInputStream input5 = new FileInputStream(file5);
	    MultipartFile multipartFile5 = new MockMultipartFile("file",
	            file5.getName(), "application/pdf", IOUtils.toByteArray(input5));
	     
	    DBFile db5 = dbFileService.storeFile(multipartFile5);
	    
	    
	    File file6 = new File(fpp + "Mineraloska, fizicko-hemijska i keramicka svojstva gline Brezaci.pdf");
	    FileInputStream input6 = new FileInputStream(file6);
	    MultipartFile multipartFile6 = new MockMultipartFile("file",
	            file6.getName(), "application/pdf", IOUtils.toByteArray(input6));
	     
	    DBFile db6 = dbFileService.storeFile(multipartFile6);
	    
	    
	    File file7 = new File(fpp + "Hemijska industrija.pdf");
	    FileInputStream input7 = new FileInputStream(file7);
	    MultipartFile multipartFile7 = new MockMultipartFile("file",
	            file7.getName(), "application/pdf", IOUtils.toByteArray(input7));
	     
	    DBFile db7 = dbFileService.storeFile(multipartFile7);
	    
	    

	    File file8 = new File(fpp + "Zastita materijala.pdf");
	    FileInputStream input8 = new FileInputStream(file8);
	    MultipartFile multipartFile8 = new MockMultipartFile("file",
	            file8.getName(), "application/pdf", IOUtils.toByteArray(input8));
	     
	    DBFile db8 = dbFileService.storeFile(multipartFile8);
	    
	    
	
	}

}
