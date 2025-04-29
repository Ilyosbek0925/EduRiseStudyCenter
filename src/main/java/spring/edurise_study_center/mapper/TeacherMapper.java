package spring.edurise_study_center.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.edurise_study_center.DTO.request.TeacherDto;
import spring.edurise_study_center.DTO.response.TeacherResponse;
import spring.edurise_study_center.entity.Teacher;

import java.util.List;
@Component

public class TeacherMapper {


    private final PasswordEncoder passwordEncoder;

    public TeacherMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Teacher toEntity(TeacherDto dto) {
        if (dto == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setPhoneNumber(dto.getPhoneNumber());
        teacher.setUsername(dto.getUsername());
        teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        return teacher;
    }

    public TeacherResponse toTeacherResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .phoneNumber(teacher.getPhoneNumber())
                .teacherId(teacher.getId())
                .subject(teacher.getSubject())
                .build();
    }
public List<TeacherResponse> toTeacherResponseList(List<Teacher> teachers) {
 return teachers.stream().map(this::toTeacherResponse).toList();
}

}
