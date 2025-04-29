package spring.edurise_study_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.edurise_study_center.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
