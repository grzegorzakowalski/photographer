package com.kowal.photographer.repositorys;

import com.kowal.photographer.entities.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAllByResolved(boolean resolved);
}
