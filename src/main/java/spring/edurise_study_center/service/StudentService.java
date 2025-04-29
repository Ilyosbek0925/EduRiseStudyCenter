package spring.edurise_study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.edurise_study_center.DTO.request.StudentDto;
import spring.edurise_study_center.DTO.response.StudentResponse;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.mapper.StudentMapper;
import spring.edurise_study_center.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponse addStudent(StudentDto studentDto) {
        Student student = studentMapper.toStudent(studentDto);
        return studentMapper.toStudentResponse(studentRepository.save(student));
    }


    public StudentResponse findById(int id) {
        StudentResponse student = studentMapper.toStudentResponse(studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found")));
        return student;

    }

    public StudentResponse update(StudentDto student, int id) {
        Student student1 = studentMapper.toStudent(student);
        student1.setId(id);
        return studentMapper.toStudentResponse(studentRepository.save(student1));
    }

    public void delete(int id) {
        studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.deleteById(id);
    }

    public List<StudentResponse> findAllByGroupId(int groupId) {
        List<Group> group = studentRepository.findById(groupId).orElseThrow(() -> new RuntimeException("group not found")).getGroups();
        return studentMapper.toStudentResponseList(group.get(groupId).getStudents());
    }
}
