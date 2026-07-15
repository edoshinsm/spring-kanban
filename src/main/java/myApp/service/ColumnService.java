package myApp.service;

import myApp.model.EntityColumn;
import myApp.model.EntityIssue;
import myApp.repository.ColumnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {
    private final ColumnRepository columnRepository;

    ColumnService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public List<EntityColumn> findByProjectId(Long id) {
        return columnRepository.findByProjectId(id);
    }

    public List<EntityColumn> getColumns() {
        return columnRepository.findAll();
    }

    public EntityColumn getColumnById(Long columnId) {
        return columnRepository.findById(columnId).orElse(null);
    }

    public void saveColumn(EntityColumn column) {
        column.setPosition(columnRepository.count() + 1L);
        columnRepository.save(column);
    }

    public void deleteColumnById(Long columnId) {
        columnRepository.deleteById(columnId);
    }
}
