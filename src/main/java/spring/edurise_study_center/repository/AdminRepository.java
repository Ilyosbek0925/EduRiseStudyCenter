package spring.edurise_study_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.edurise_study_center.entity.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin , Integer> {


    Optional<Admin> findByUsername(String username);
}
