package spring.edurise_study_center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.edurise_study_center.DTO.request.TeacherDto;
import spring.edurise_study_center.DTO.response.TeacherResponse;
import spring.edurise_study_center.entity.Teacher;
import spring.edurise_study_center.mapper.TeacherMapper;
import spring.edurise_study_center.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;
@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherResponse addTeacher(TeacherDto teacherDto) {
        return teacherMapper.toTeacherResponse(teacherRepository.save(teacherMapper.toEntity(teacherDto)));
    }

    public List<TeacherResponse> getAll() {
        return teacherMapper.toTeacherResponseList(teacherRepository.findAll());
    }


    public TeacherResponse findById(int id) {
        Optional<Teacher> byId = teacherRepository.findById(id);
        return teacherMapper.toTeacherResponse(byId.orElseThrow(() -> new RuntimeException("not found teacher")));
    }

    public TeacherResponse update(TeacherDto teacherDto, int id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("not found teacher"));
        teacher.setId(id);
        return teacherMapper.toTeacherResponse(teacherRepository.save(teacher));

    }


    public void delete(int id) {
        teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("not found teacher"));
        teacherRepository.deleteById(id);
    }
}
