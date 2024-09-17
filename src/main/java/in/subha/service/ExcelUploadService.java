package in.subha.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.subha.entity.Courses;
import in.subha.repo.CoursesRepo;

@Service
public class ExcelUploadService {

	@Autowired
	private CoursesRepo courseRepo;

	public static boolean isValidExcelFile(MultipartFile file) {
		return Objects.equals(file.getContentType(),
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	public void importExcelData(MultipartFile file) throws IOException {
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0); // Assumes you are reading the first sheet
		Courses c = new Courses();
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // Start from row 1 to skip header
			Row row = sheet.getRow(i);

			// Read column values
			c.setId((int) row.getCell(0).getNumericCellValue());
			c.setName( row.getCell(1).getStringCellValue());
			c.setFees(row.getCell(2).getNumericCellValue());
			c.setDuration(row.getCell(3).getStringCellValue());
			c.setStartDate(DateUtil.getJavaDate(row.getCell(4).getNumericCellValue()).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
			c.setCompletionDate(DateUtil.getJavaDate(row.getCell(5).getNumericCellValue()).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
					
			// Save to database
			courseRepo.save(c);
		}

		workbook.close();
	}

	

//	public static List<Courses> getCoursesDataFromExcel(InputStream inputStream) {
//		List<Courses> courses = new ArrayList<>();
//		try {
//			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//			XSSFSheet sheet = workbook.getSheet("Courses Info");
//			int rowIndex = 0;
//			for (Row row : sheet) {
//				if (rowIndex == 0) {
//					rowIndex++;
//					continue;
//				}
//				Iterator<Cell> cellIterator = row.iterator();
//				int cellIndex = 0;
//				Courses c = new Courses();
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					switch (cellIndex) {
//					case 0 -> c.setId((int) cell.getNumericCellValue());
//					case 1 -> c.setName(cell.getStringCellValue());
//					case 2 -> c.setFees(cell.getNumericCellValue());
//					case 3 -> c.setDuration(cell.getStringCellValue());
//					case 4 -> c.setStartDate(DateUtil.getJavaDate(cell.getNumericCellValue()).toInstant()
//							.atZone(ZoneId.systemDefault()).toLocalDateTime());
//					case 5 -> c.setCompletionDate(DateUtil.getJavaDate(cell.getNumericCellValue()).toInstant()
//							.atZone(ZoneId.systemDefault()).toLocalDate());
//					default -> {
//					}
//					}
//					cellIndex++;
//				}
//				courses.add(c);
//			}
//		} catch (IOException e) {
//			e.getStackTrace();
//		}
//		return courses;
//	}

}
