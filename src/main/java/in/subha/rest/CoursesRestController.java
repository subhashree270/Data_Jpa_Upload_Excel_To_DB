package in.subha.rest;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.subha.service.ExcelUploadService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CoursesRestController {
	@Autowired
	private ExcelUploadService service;
	
	 @PostMapping("/upload")
	    public ResponseEntity<?> uploadCustomersData(@RequestParam("file")MultipartFile file) throws IOException{
	        this.service.importExcelData(file);
	        return ResponseEntity
	                .ok(Map.of("Message" , " Customers data uploaded and saved to database successfully"));
	    }

//	    @GetMapping("/")
//	    public ResponseEntity<List<Courses>> getCustomers(){
//	        return new ResponseEntity<>(service.getCourses(), HttpStatus.FOUND);
//	    }
	}