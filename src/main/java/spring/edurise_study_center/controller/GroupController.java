package spring.edurise_study_center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.edurise_study_center.DTO.request.GroupDto;
import spring.edurise_study_center.DTO.response.GroupResponse;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.repository.GroupRepository;
import spring.edurise_study_center.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/addGroup")
    public ResponseEntity<?> addGroup(@RequestBody GroupDto groupDto) {
        GroupResponse group = groupService.addGroup(groupDto);
        System.out.println("group is "+group);
        return ResponseEntity.ok().body(group);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GroupResponse>> getAllGroup() {
        List<GroupResponse> groups = groupService.getAll();
        return ResponseEntity.ok().body(groups);
    }

    @GetMapping("/findGroupById/{id}")
    public ResponseEntity<?> findById(@RequestParam int id) {
        GroupResponse group = groupService.findById(id);
        return ResponseEntity.ok().body(group);
    }

    @PutMapping("modifyGroup/{groupId}")
    public ResponseEntity<?> modifyGroup(@RequestBody GroupDto groupDto, @PathVariable int groupId) {
        GroupResponse group = groupService.modify(groupDto,groupId);
        return ResponseEntity.ok().body(group);
    }
    @DeleteMapping("deleteGroup")
    public ResponseEntity<?> deleteGroup(@RequestBody GroupDto groupDto) {
        groupService.delete(groupDto);
        return ResponseEntity.status(204).build();
    }
}
