package spring.edurise_study_center.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.edurise_study_center.DTO.request.GroupDto;
import spring.edurise_study_center.DTO.response.GroupResponse;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.entity.Teacher;
import spring.edurise_study_center.repository.StudentRepository;
import spring.edurise_study_center.repository.TeacherRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GroupMapper {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
private final TeacherMapper teacherMapper;


    public Group toEntity(GroupDto dto) {
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        Group group = new Group();
        group.setGroupName(dto.getGroupName());
        group.setStartDate(dto.getStartDate());
        group.setTeacher(teacher);
        group.setStartTime(dto.getStartTime());
        group.setDay(dto.getDays());
        return group;
    }


    public GroupResponse toGroupResponse(Group group) {

return GroupResponse.builder().groupName(group.getGroupName())
        .startDate(group.getStartDate())
        .startTime(group.getStartTime())
        .day(group.getDay())
        .teacher(teacherMapper.toTeacherResponse(group.getTeacher()))
        .groupName(group.getGroupName())
        .groupId(group.getId())
        .build();
    }
    public List<GroupResponse> toGroupResponseList(List<Group> groups) {
        if (groups == null) {
            return Collections.emptyList(); // yoki yangi List qaytarish
        }
        return groups.stream()
                .map(this::toGroupResponse)
                .collect(Collectors.toList());
    }

}
