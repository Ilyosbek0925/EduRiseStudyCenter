package spring.edurise_study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.edurise_study_center.DTO.request.AttendanceDto;
import spring.edurise_study_center.DTO.response.AttendanceResponse;
import spring.edurise_study_center.entity.Attendance;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.mapper.AttendanceMapper;
import spring.edurise_study_center.repository.AttendanceRepository;
import spring.edurise_study_center.repository.GroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final GroupRepository groupRepository;

    public List<AttendanceResponse> add(List<AttendanceDto> attendanceDto,int groupId) {
        List<Attendance> attendance = attendanceMapper.toAttendanceList(attendanceDto,groupId);
        return attendanceMapper.toAttendanceResponseList(attendanceRepository.saveAll(attendance));
    }

    public List<AttendanceResponse> getAll() {
        return attendanceMapper.toAttendanceResponseList(attendanceRepository.findAll());
    }

    public AttendanceResponse findById(int id) {
        return attendanceMapper.toAttendanceResponse(attendanceRepository.findById(id).orElseThrow(() -> new RuntimeException("not found")));
    }

    public AttendanceResponse update(int id, AttendanceDto attendanceDto) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Attendance not found"));
        Attendance attendance1 = attendanceMapper.toAttendance(attendanceDto);
        attendance1.setId(id);
        return attendanceMapper.toAttendanceResponse(attendanceRepository.save(attendance1));
    }

    public void delete(int id) {
        attendanceRepository.deleteById(id);
    }

    public List<AttendanceResponse> findAllById(int groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("group not found"));
return attendanceMapper.toAttendanceResponseList(group.getAttendance());
    }
}
