package spring.edurise_study_center.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.edurise_study_center.DTO.request.AttendanceDto;
import spring.edurise_study_center.DTO.response.AttendanceResponse;
import spring.edurise_study_center.entity.Attendance;
import spring.edurise_study_center.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
@Autowired
 AttendanceService attendanceService;

    @PostMapping("/markAttendance/{groupId}")
    public ResponseEntity<?> add(@RequestBody List<AttendanceDto> attendanceDto, @PathVariable int groupId) {
        return ResponseEntity.ok().body(attendanceService.add(attendanceDto,groupId));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<AttendanceResponse>> getAll() {
        return ResponseEntity.ok().body(attendanceService.getAll());
    }

    @GetMapping("/getAttendancesByGroupId/{groupId}")
    public ResponseEntity<List<AttendanceResponse>> findById(@RequestParam int id) {
        List<AttendanceResponse> attendance = attendanceService.findAllById(id);
        return ResponseEntity.ok().body(attendance);
    }

    @PutMapping("modify/{id}")
    public ResponseEntity<AttendanceResponse> modify(@RequestBody AttendanceDto attendanceDto, @PathVariable int id) {
        AttendanceResponse attendance = attendanceService.update(id, attendanceDto);
        return ResponseEntity.ok().body(attendance);
    }

    @DeleteMapping("deleteAttendance/{id}")
    public ResponseEntity<AttendanceResponse> delete(@RequestParam int id) {
        attendanceService.delete(id);
        return ResponseEntity.status(204).build();
    }

}
