package com.logintegra.wsbbugtracker.issues;

import com.logintegra.wsbbugtracker.enums.State;
import com.logintegra.wsbbugtracker.projects.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class IssueQueryExample {

    final EntityManager entityManager;
    final ProjectRepository projectRepository;

    public IssueQueryExample(EntityManager entityManager, ProjectRepository projectRepository) {
        this.entityManager = entityManager;
        this.projectRepository = projectRepository;
    }

    public List<Issue> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Issue> query = builder.createQuery(Issue.class);

        Root<Issue> issueRoot = query.from(Issue.class);
        CriteriaQuery<Issue> select = query.select(issueRoot);

        TypedQuery<Issue> typedQuery = entityManager.createQuery(select);
        List<Issue> issues = typedQuery.getResultList();

        return issues;
    }

    public List<Issue> findAllToDo() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Issue> query = builder.createQuery(Issue.class);

        Root<Issue> issueRoot = query.from(Issue.class);
        CriteriaQuery<Issue> select = query.select(issueRoot);

        // Warunek
        Predicate isToDo = builder.equal(issueRoot.get("state"), State.TODO);

        TypedQuery<Issue> typedQuery = entityManager.createQuery(select.where(isToDo));
        List<Issue> issues = typedQuery.getResultList();

        return issues;
    }

    public List<Issue> findAllToDoAndProjectIsABC() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Issue> query = builder.createQuery(Issue.class);

        Root<Issue> issueRoot = query.from(Issue.class);
        CriteriaQuery<Issue> select = query.select(issueRoot);

        // Warunki
        Predicate isToDo = builder.equal(issueRoot.get("state"), State.TODO);
        Predicate projectIsABC = builder.equal(issueRoot.get("project"), projectRepository.findByName("ABC"));

        TypedQuery<Issue> typedQuery = entityManager.createQuery(select.where(builder.and(isToDo, projectIsABC)));
        List<Issue> issues = typedQuery.getResultList();

        return issues;
    }
}
