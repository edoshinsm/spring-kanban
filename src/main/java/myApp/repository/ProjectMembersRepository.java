package myApp.repository;

import myApp.model.EntityProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMembersRepository extends JpaRepository<EntityProjectMembers, Long> {
}
