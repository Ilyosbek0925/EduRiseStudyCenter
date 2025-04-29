package spring.edurise_study_center.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends BaseEntity {
    private LocalDate paidDate;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private double amount;
    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
