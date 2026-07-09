package myApp.repository;

import myApp.model.EntityIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<EntityIssue, Long> {

}
