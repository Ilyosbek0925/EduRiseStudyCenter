package spring.edurise_study_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.edurise_study_center.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByUsername(String teacherName);
}
