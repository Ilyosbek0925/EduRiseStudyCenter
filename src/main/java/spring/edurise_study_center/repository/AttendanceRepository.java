package spring.edurise_study_center.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.edurise_study_center.entity.Attendance;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
}
