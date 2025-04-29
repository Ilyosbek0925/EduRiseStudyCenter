package spring.edurise_study_center.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDto {
    private LocalDate paidDate;
    private int studentId;
    private double paymentAmount;
    private int groupId;
}
