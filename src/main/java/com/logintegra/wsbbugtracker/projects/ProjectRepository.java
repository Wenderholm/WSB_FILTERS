package com.logintegra.wsbbugtracker.projects;

import com.logintegra.wsbbugtracker.issues.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
