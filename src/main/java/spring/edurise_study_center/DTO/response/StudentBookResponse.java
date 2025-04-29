package spring.edurise_study_center.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import spring.edurise_study_center.entity.Group;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class StudentBookResponse {
    private Integer bookId;
    private String originalName;
    private String downloadUrl;
    private String contextType;
    private Group group;
    private byte[] byteFile;
}
