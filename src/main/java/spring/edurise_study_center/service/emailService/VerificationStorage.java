package spring.edurise_study_center.service.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.edurise_study_center.DTO.request.StudentDto;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.entity.TempUser;
import spring.edurise_study_center.entity.VerificationStatus;
import spring.edurise_study_center.service.StudentService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class VerificationStorage {

    @Autowired
    StudentService studentService;
    Map<String, TempUser> tempStorage = new HashMap<>();

    public String register(StudentDto studentDto) {
        TempUser tempUser = TempUser.builder()
                .age(studentDto.getAge())
                .email(studentDto.getEmail())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .phoneNumber(studentDto.getPhoneNumber())
                .password(studentDto.getPassword())
                .sentCode(Email.sendCode(studentDto.getEmail()))
                .build();
        tempStorage.put(studentDto.getEmail(), tempUser);
        return VerificationStatus.CHECK_EMAIL.name();
    }


    public String verify(String email, String code) {
        Optional<TempUser> first = tempStorage.values().stream().filter(tempUser -> tempUser.getEmail().equals(email)).findFirst();
        if (first.isPresent()) {
            TempUser tempUser = first.get();
            StudentDto studentDto = new StudentDto();
            studentDto.setEmail(email);
            studentDto.setPassword(tempUser.getPassword());
            studentDto.setFirstName(tempUser.getFirstName());
            studentDto.setLastName(tempUser.getLastName());
            studentDto.setPhoneNumber(tempUser.getPhoneNumber());
            studentDto.setAge(tempUser.getAge());
            studentService.addStudent(studentDto);
            return VerificationStatus.VERIFIED.name();
        }
        return VerificationStatus.FAILED.name();
    }


}
