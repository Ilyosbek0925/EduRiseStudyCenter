package spring.edurise_study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.edurise_study_center.DTO.request.PaymentDto;
import spring.edurise_study_center.DTO.response.AttendanceResponse;
import spring.edurise_study_center.DTO.response.PaymentResponse;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Payment;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.mapper.PaymentMapper;
import spring.edurise_study_center.repository.GroupRepository;
import spring.edurise_study_center.repository.PaymentRepository;
import spring.edurise_study_center.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public PaymentResponse add(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toPayment(paymentDto);
        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }


    public List<PaymentResponse> getAll() {
        return paymentMapper.toPaymentResponseList(paymentRepository.findAll());
    }

    public List<PaymentResponse> findPaymentsById(int groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("group not found"));
        return paymentMapper.toPaymentResponseList(group.getPayments());
    }


    public PaymentResponse update(PaymentDto paymentDto, int id) {
        return paymentMapper.toPaymentResponse(paymentRepository.save(paymentMapper.toPayment(paymentDto)));
    }

    public void delete(int paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
