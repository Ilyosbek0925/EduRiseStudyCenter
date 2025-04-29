package spring.edurise_study_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.edurise_study_center.entity.StudentBooks;

import java.util.Optional;

@Repository
public interface StudentBookRepository extends JpaRepository<StudentBooks,Integer> {
    Optional<StudentBooks> findByServerName(String serverName);
    boolean existsByServerName(String serverName);
}
