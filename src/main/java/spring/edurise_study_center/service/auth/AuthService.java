package spring.edurise_study_center.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.edurise_study_center.DTO.response.LoginRequest;
import spring.edurise_study_center.entity.Admin;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.entity.Teacher;
import spring.edurise_study_center.repository.AdminRepository;
import spring.edurise_study_center.repository.StudentRepository;
import spring.edurise_study_center.repository.TeacherRepository;
import spring.edurise_study_center.service.jwt.JwtService;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    TeacherRepository teacherRepo;
    @Autowired
    StudentRepository studentRepo;
    @Autowired
    JwtService jwtService;
    @Autowired
    AdminRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Teacher> teacher = teacherRepo.findByUsername(username);
        if (teacher.isPresent()) {
            return teacher.get();
        }
        Optional<Admin> admin = adminRepo.findByUsername(username);
        if (admin.isPresent()) {
            return admin.get();
        }
        Optional<Student> student = studentRepo.findByUsername(username);
        return student.orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
