package spring.edurise_study_center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.edurise_study_center.DTO.request.StudentDto;
import spring.edurise_study_center.DTO.response.StudentResponse;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.service.StudentService;
import spring.edurise_study_center.service.emailService.VerificationStorage;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
@Autowired
StudentService studentService;
@Autowired
    VerificationStorage verificationStorage;

    @PostMapping("/verified")
    public ResponseEntity<?> addGroup(@RequestParam String email, @RequestParam String code) {
        String verify = verificationStorage.verify(email, code);
        return ResponseEntity.ok().body(verify);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addStudent(@RequestBody StudentDto student) {
        String register = verificationStorage.register(student);
        return ResponseEntity.ok().body(register);
    }

    @GetMapping("getStudentsByGroupId/{groupId}")
    public ResponseEntity<List<StudentResponse>> getAllStudents(@PathVariable int groupId) {
        List<StudentResponse> students = studentService.findAllByGroupId(groupId);
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/findStudentById/{studentId}")
    public ResponseEntity<?> findById(@PathVariable(name = "studentId") int id) {
        StudentResponse student = studentService.findById(id);
        return ResponseEntity.ok().body(student);
    }

    @PutMapping("modifyStudent/{studentId}")
    public ResponseEntity<?> modify(@RequestBody StudentDto student, @PathVariable int studentId) {
        StudentResponse student1 = studentService.update(student, studentId);
        return ResponseEntity.ok().body(student1);
    }

    @DeleteMapping("delete/{studentId}")
    public ResponseEntity<?> delete(@PathVariable int studentId) {
        studentService.delete(studentId);
        return ResponseEntity.status(204).build();
    }


}
