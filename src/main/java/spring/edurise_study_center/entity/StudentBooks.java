package spring.edurise_study_center.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentBooks extends BaseEntity {
    private String originalName;
    private String serverName;
    private String contextType;
    private String downloadUrl;
    private String bookDesc;
    private Long size;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

}
