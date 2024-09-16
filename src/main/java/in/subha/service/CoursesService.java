package in.subha.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.subha.entity.Courses;
import in.subha.repo.CoursesRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CoursesService {
	
	private CoursesRepo repo;
	
	
	 public void saveCoursesToDatabase(MultipartFile file){
	        if(ExcelUploadService.isValidExcelFile(file)){
	            try {
	                List<Courses> courses = ExcelUploadService.getCoursesDataFromExcel(file.getInputStream());
	                this.repo.saveAll(courses);
	            } catch (IOException e) {
	                throw new IllegalArgumentException("The file is not a valid excel file");
	            }
	        }
	    }

	    public List<Courses> getCustomers(){
	        return repo.findAll();
	    }
	}


