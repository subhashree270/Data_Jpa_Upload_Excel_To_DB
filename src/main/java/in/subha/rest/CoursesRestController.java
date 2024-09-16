package in.subha.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.subha.entity.Courses;
import in.subha.service.CoursesService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CoursesRestController {
	
	private CoursesService service;
	
	 @PostMapping("/upload")
	    public ResponseEntity<?> uploadCustomersData(@RequestParam("file")MultipartFile file){
	        this.service.saveCoursesToDatabase(file);
	        return ResponseEntity
	                .ok(Map.of("Message" , " Customers data uploaded and saved to database successfully"));
	    }

	    @GetMapping("/")
	    public ResponseEntity<List<Courses>> getCustomers(){
	        return new ResponseEntity<>(service.getCourses(), HttpStatus.FOUND);
	    }
	}