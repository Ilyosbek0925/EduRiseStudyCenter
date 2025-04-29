package spring.edurise_study_center.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.edurise_study_center.DTO.request.AttendanceDto;
import spring.edurise_study_center.DTO.response.AttendanceResponse;
import spring.edurise_study_center.entity.Attendance;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.entity.Student;
import spring.edurise_study_center.repository.GroupRepository;
import spring.edurise_study_center.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AttendanceMapper {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;

    public Attendance toAttendance(AttendanceDto attendance) {
        Student student = studentRepository.findById(attendance.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found"));
        return Attendance.builder().isAttendance(attendance.isStatus()).attendanceDate(LocalDate.now()).studentId(student).build();
    }


    public AttendanceResponse toAttendanceResponse(Attendance attendance) {
        return AttendanceResponse.builder()
                .attendanceId(attendance.getId())
                .groupId(groupMapper.toGroupResponse(attendance.getGroupId()))
                .isAttendance(attendance.isAttendance())
                .attendanceDate(attendance.getAttendanceDate())
                .studentId(studentMapper.toStudentResponse(attendance.getStudentId()))
                .build();
    }

    public List<AttendanceResponse> toAttendanceResponseList(List<Attendance> attendanceList) {
        if (attendanceList == null || attendanceList.isEmpty()) {
            return null;
        }
        return attendanceList.stream().map(this::toAttendanceResponse).toList();
    }


    public List<Attendance> toAttendanceList(List<AttendanceDto> attendanceDto, int groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        List<Attendance> list = attendanceDto.stream().map(this::toAttendance).map(attendance -> {
            attendance.setGroupId(group);
            return attendance;
        }).toList();
        return list;
    }
}
