package spring.edurise_study_center.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String subject;
}
