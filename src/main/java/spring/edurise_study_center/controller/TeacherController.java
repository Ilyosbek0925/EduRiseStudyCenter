package spring.edurise_study_center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.edurise_study_center.DTO.request.TeacherDto;
import spring.edurise_study_center.DTO.response.TeacherResponse;
import spring.edurise_study_center.entity.Teacher;
import spring.edurise_study_center.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/addTeacher")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherDto teacherDto) {
        TeacherResponse teacher = teacherService.addTeacher(teacherDto);
        return ResponseEntity.ok().body(teacher);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<TeacherResponse>> getAllTeacher() {
        List<TeacherResponse> teachers = teacherService.getAll();
        return ResponseEntity.ok().body(teachers);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@RequestParam int id) {
        TeacherResponse teacher = teacherService.findById(id);
        return ResponseEntity.ok().body(teacher);
    }

    @PutMapping("modifyTeacher/{id}")
    public ResponseEntity<?> modifyGroup(@RequestBody TeacherDto teacherDto, @PathVariable int id) {
        TeacherResponse teacher = teacherService.update(teacherDto, id);
        return ResponseEntity.ok().body(teacher);
    }

    @DeleteMapping("deleteTeacher/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable int id) {
        teacherService.delete(id);
        return ResponseEntity.status(204).build();
    }


}
