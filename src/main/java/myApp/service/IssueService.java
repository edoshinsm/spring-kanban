package myApp.service;

import myApp.model.EntityIssue;
import myApp.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<EntityIssue> findByColumnId(Long columnId) {
        return issueRepository.findByColumnId(columnId);
    }

    public EntityIssue getIssueById(Long issueId) {
        return issueRepository.findById(issueId).orElse(null);
    }

    public void saveIssue(EntityIssue issue) {
        issueRepository.save(issue);
    }

    public void deleteIssueById(Long issueId) {
        issueRepository.deleteById(issueId);
    }

    public List<EntityIssue> findByProjectId(Long projectId) {
        return issueRepository.findByProjectId(projectId);
    }
}