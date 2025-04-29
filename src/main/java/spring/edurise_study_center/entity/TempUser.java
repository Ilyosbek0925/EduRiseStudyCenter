package spring.edurise_study_center.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TempUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private String phoneNumber;
    private String sentCode;


}
