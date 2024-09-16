package in.subha.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="course_tbl")
public class Courses {
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="fees")
	private Double fees;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="completion_date")
	private LocalDate completionDate;

}
