package spring.edurise_study_center.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Student;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    private Integer paymentId;
    private LocalDate paidDate;
    private StudentResponse student;
    private double amount;
    private GroupResponse group;
}
