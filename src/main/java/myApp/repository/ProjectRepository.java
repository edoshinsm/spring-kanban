package myApp.repository;

import myApp.model.EntityProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<EntityProject, Long> {
    Optional<EntityProject> findById(Long id);
    Optional<EntityProject> findByInviteCode(String inviteCode);
}
