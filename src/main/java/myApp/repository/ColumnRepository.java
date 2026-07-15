package myApp.repository;

import myApp.model.EntityColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends JpaRepository<EntityColumn, Long> {
    List<EntityColumn> findByProjectId(Long projectId);
}
