package com.innocito.apitestingplatform.repository;

import com.innocito.apitestingplatform.entity.ProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectsRepository extends JpaRepository<ProjectsEntity, Long> {


}
