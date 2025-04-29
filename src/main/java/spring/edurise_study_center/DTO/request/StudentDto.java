package spring.edurise_study_center.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.edurise_study_center.entity.Roles;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private String phoneNumber;


}
