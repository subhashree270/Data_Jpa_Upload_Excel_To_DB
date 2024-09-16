package in.subha.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import in.subha.entity.Courses;

public class ExcelUploadService {
	
	public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
   public static List<Courses> getCoursesDataFromExcel(InputStream inputStream){
        List<Courses> courses = new ArrayList<>();
       try {
           XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
           XSSFSheet sheet = workbook.getSheet("Courses Info");
           int rowIndex =0;
           for (Row row : sheet){
               if (rowIndex ==0){
                   rowIndex++;
                   continue;
               }
               Iterator<Cell> cellIterator = row.iterator();
               int cellIndex = 0;
               Courses c = new Courses();
               while (cellIterator.hasNext()){
                   Cell cell = cellIterator.next();
                   switch (cellIndex){
                       case 0 -> c.setId((int) cell.getNumericCellValue());
                       case 1 -> c.setName(cell.getStringCellValue());
                       case 2 -> c.setFees(cell.getNumericCellValue());
                       case 3 -> c.setDuration(cell.getStringCellValue());
                       case 4 -> c.setStartDate(DateUtil.getJavaDate(cell.getNumericCellValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                       case 5 -> c.setCompletionDate(DateUtil.getJavaDate(cell.getNumericCellValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                       default -> {
                       }
                   }
                   cellIndex++;
               }
               courses.add(c);
           }
       } catch (IOException e) {
           e.getStackTrace();
       }
       return courses;
   }

}


