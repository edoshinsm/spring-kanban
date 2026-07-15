package myApp.repository;

import myApp.model.EntityProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMembersRepository extends JpaRepository<EntityProjectMembers, Long> {
    boolean existsByProjectIdAndUserId(Long projectId, Long userId);
    List<EntityProjectMembers> findByUserId(Long userId);
    List<EntityProjectMembers> findByProjectId(Long projectId);
}
