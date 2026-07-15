package myApp.repository;

import myApp.model.EntityColumn;
import myApp.model.EntityIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<EntityIssue, Long> {
    List<EntityIssue> findByColumnId(Long columnId);

    @Query("SELECT i FROM EntityIssue i JOIN i.column c WHERE c.project.id = :projectId")
    List<EntityIssue> findByProjectId(@Param("projectId") Long projectId);
}
