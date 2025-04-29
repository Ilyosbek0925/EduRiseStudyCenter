package spring.edurise_study_center.DTO.response;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Student;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceResponse {
    private Integer attendanceId;
    private  boolean isAttendance;
    private LocalDate attendanceDate;
    private GroupResponse groupId;
    private StudentResponse studentId;

}
