package spring.edurise_study_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.edurise_study_center.entity.Group;
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {


    Group findByGroupName(String name);


}
