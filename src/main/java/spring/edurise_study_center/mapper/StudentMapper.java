package spring.edurise_study_center.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.edurise_study_center.DTO.request.StudentDto;
import spring.edurise_study_center.DTO.response.StudentResponse;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Roles;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.repository.GroupRepository;
import spring.edurise_study_center.repository.PaymentRepository;
import spring.edurise_study_center.repository.StudentRepository;
import spring.edurise_study_center.service.jwt.JwtService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final PasswordEncoder passwordEncoder;


    private final StudentRepository studentRepository;
private final GroupRepository groupRepository;
private final PaymentRepository paymentRepository;
private final GroupMapper groupMapper;
private final JwtService jwtService;
public Student toStudent(StudentDto studentDto) {

    Student student = new Student();
student.setAge(studentDto.getAge());
student.setFirstName(studentDto.getFirstName());
student.setLastName(studentDto.getLastName());
student.setPhoneNumber(studentDto.getPhoneNumber());
student.setPhoneNumber(studentDto.getPhoneNumber());
student.setRole(Roles.STUDENT);
student.setUsername(studentDto.getEmail());
student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
return student;
}

public StudentResponse toStudentResponse(Student student) {
    return StudentResponse.builder()
            .studentId(student.getId())
            .age(student.getAge())
            .firstName(student.getFirstName())
            .lastName(student.getLastName())
            .phoneNumber(student.getPhoneNumber())
            .build();
}

public List<StudentResponse>toStudentResponseList(List<Student> studentList) {
    return studentList.stream().map(this::toStudentResponse).toList();
}

}
