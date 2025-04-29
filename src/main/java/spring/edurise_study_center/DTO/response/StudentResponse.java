package spring.edurise_study_center.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Payment;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class StudentResponse {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String jwtToken;
    private List<GroupResponse> group;
    private List<PaymentResponse>payment;

}
