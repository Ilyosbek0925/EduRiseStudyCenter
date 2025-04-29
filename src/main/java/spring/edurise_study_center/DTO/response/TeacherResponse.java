package spring.edurise_study_center.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import spring.edurise_study_center.entity.Group;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class TeacherResponse {
    private Integer teacherId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String subject;
    private String jwtToken;
    private List<GroupResponse> groups;
}
