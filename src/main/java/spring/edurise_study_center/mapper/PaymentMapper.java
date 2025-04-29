package spring.edurise_study_center.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.edurise_study_center.DTO.request.PaymentDto;
import spring.edurise_study_center.DTO.response.PaymentResponse;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Payment;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.repository.GroupRepository;
import spring.edurise_study_center.repository.PaymentRepository;
import spring.edurise_study_center.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PaymentMapper {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;

    public Payment toPayment(PaymentDto paymentDto) {
        Group group = groupRepository.findById(paymentDto.getGroupId()).orElseThrow(() -> new RuntimeException("group not found"));
        Student student = studentRepository.findById(paymentDto.getStudentId()).orElseThrow(() -> new RuntimeException("student not found"));
        return Payment.builder()
                .amount(paymentDto.getPaymentAmount())
                .group(group)
                .student(student)
                .paidDate(LocalDate.now())
                .build();
    }

    public PaymentResponse toPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .amount(payment.getAmount())
                .group(groupMapper.toGroupResponse(payment.getGroup()))
                .student(studentMapper.toStudentResponse(payment.getStudent()))
                .paidDate(payment.getPaidDate())
                .build();
    }

    public List<PaymentResponse> toPaymentResponseList(List<Payment> payments) {
        return payments.stream().map(payment -> toPaymentResponse(payment)).toList();
    }

}
