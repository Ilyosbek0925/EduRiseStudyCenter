package spring.edurise_study_center.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Attendance extends BaseEntity {
private  boolean isAttendance;
private LocalDate attendanceDate;
@ManyToOne
@JoinColumn(name="group_id")
private  Group groupId;
@ManyToOne
@JoinColumn(name = "student_id")
private Student studentId;
}
