package myApp.service;

import myApp.model.EntityColumn;
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

    public void saveColumn(EntityColumn column) {
        columnRepository.save(column);
    }
}
