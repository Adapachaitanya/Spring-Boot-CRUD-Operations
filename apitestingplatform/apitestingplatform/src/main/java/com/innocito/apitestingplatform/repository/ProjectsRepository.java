package com.innocito.apitestingplatform.repository;

import com.innocito.apitestingplatform.apiresponse.ApiResponseMessage;
import com.innocito.apitestingplatform.entity.ProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProjectsRepository extends JpaRepository<ProjectsEntity, Long>
{
   Optional<ProjectsEntity> findByProjectName(String projectName);
   List<ProjectsEntity> findByCreatedByContainingIgnoreCase(String createdBy);
   List<ProjectsEntity> findByCreationDate(LocalDate creationDate);

}
