package spring.edurise_study_center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.edurise_study_center.DTO.request.GroupDto;
import spring.edurise_study_center.DTO.response.GroupResponse;
import spring.edurise_study_center.entity.Group;
import spring.edurise_study_center.mapper.GroupMapper;
import spring.edurise_study_center.repository.GroupRepository;
import spring.edurise_study_center.repository.StudentRepository;
import spring.edurise_study_center.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final GroupMapper groupMapper;

    public GroupResponse addGroup(GroupDto groupDto) {
        Group entity = groupMapper.toEntity(groupDto);
        return groupMapper.toGroupResponse(groupRepository.save(entity));
    }





    public List<GroupResponse> getAll() {
        List<Group> groups = groupRepository.findAll();
        return groupMapper.toGroupResponseList(groups);
    }

    public GroupResponse findById(int id) {
        Optional<Group> byId = groupRepository.findById(id);
        return groupMapper.toGroupResponse(byId.orElseThrow(() -> new RuntimeException("Group not found")));
    }

    public GroupResponse modify(GroupDto groupDto,int groupId) {
        Group entity = groupMapper.toEntity(groupDto);
        entity.setId(groupId);
        return groupMapper.toGroupResponse(groupRepository.save(entity));
    }

    public void delete(GroupDto groupDto) {
    groupRepository.delete(groupMapper.toEntity(groupDto));
    }
}
