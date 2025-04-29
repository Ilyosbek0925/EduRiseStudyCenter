package spring.edurise_study_center.entity;

import ch.qos.logback.classic.boolex.StubEventEvaluator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "study_group")
public class Group extends BaseEntity {
    private String groupName;
    private LocalDate startDate;
private LocalTime startTime;
    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "group_days", joinColumns = @JoinColumn(name = "group_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> day;
    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;
    @ManyToMany
    @JoinTable(
            name = "group_student",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonManagedReference

    private List<Student> students;
    @OneToMany(mappedBy = "groupId")
    private List<Attendance> attendance;
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment>payments;
    @OneToMany(mappedBy ="group",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentBooks> studentBooks;

}
