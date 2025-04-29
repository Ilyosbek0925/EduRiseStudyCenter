package spring.edurise_study_center.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import spring.edurise_study_center.entity.Attendance;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.entity.Teacher;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupResponse {
    private Integer groupId;
    private String groupName;
    private LocalDate startDate;
    private LocalTime startTime;
    private Set<DayOfWeek> day;
    private TeacherResponse teacher;
    List<StudentResponse> students;
}
