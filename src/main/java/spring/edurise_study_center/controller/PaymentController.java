package spring.edurise_study_center.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.edurise_study_center.DTO.request.AttendanceDto;
import spring.edurise_study_center.DTO.request.PaymentDto;
import spring.edurise_study_center.DTO.response.AttendanceResponse;
import spring.edurise_study_center.DTO.response.PaymentResponse;
import spring.edurise_study_center.entity.Attendance;
import spring.edurise_study_center.entity.Payment;
import spring.edurise_study_center.service.PaymentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/markPayment")
    public ResponseEntity<?> add(@RequestBody PaymentDto paymentDto) {
PaymentResponse payment=paymentService.add(paymentDto);
        return ResponseEntity.ok().body(payment);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<PaymentResponse>> getAll() {
        List<PaymentResponse> paymentResponses = paymentService.getAll();
        return ResponseEntity.ok().body(paymentResponses);
    }

    @GetMapping("/findPaymentsByGroupId/{id}")
    public ResponseEntity<?> findById(@RequestParam int groupId) {
        List<PaymentResponse> attendance = paymentService.findPaymentsById(groupId);
        return ResponseEntity.ok().body(attendance);
    }

    @PutMapping("update/{paymentId}")
    public ResponseEntity<?> modify(@RequestBody PaymentDto paymentDto, @PathVariable(name = "paymentId") int id) {
PaymentResponse paymentResponse=paymentService.update(paymentDto,id);
        return ResponseEntity.ok().body(paymentResponse);
    }

    @DeleteMapping("delete/{paymentId}")
    public ResponseEntity<?> delete(@PathVariable int paymentId) {
paymentService.delete(paymentId);
        return ResponseEntity.status(204).build();
    }


}
