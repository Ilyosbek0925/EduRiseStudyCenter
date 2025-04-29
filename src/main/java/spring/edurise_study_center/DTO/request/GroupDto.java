package spring.edurise_study_center.DTO.request;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private LocalDate startDate;
    private LocalTime startTime;
    private String groupName;
    private Set<DayOfWeek> days;
    private int teacherId;
}
