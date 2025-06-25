package com.innocito.apitestingplatform.controller;


import com.innocito.apitestingplatform.apiresponse.ApiResponseMessage;
import com.innocito.apitestingplatform.entity.ProjectsEntity;
import com.innocito.apitestingplatform.service.ProjectsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProjectsController {

    @Autowired
    private ProjectsServiceImplementation projectsServiceImplementation;

    @PostMapping("/addproject")
    public ResponseEntity<ApiResponseMessage<String>> saveuserentity(@RequestBody ProjectsEntity projectsEntity)
    {
       return projectsServiceImplementation.saveprojectsentity(projectsEntity);

    }

    @GetMapping("/allprojects")
    public ResponseEntity<ApiResponseMessage<List<ProjectsEntity>>> getallprojects()
    {
        ApiResponseMessage<List<ProjectsEntity>> response = projectsServiceImplementation.getallprojects();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("project/delete")
    public ResponseEntity<ApiResponseMessage<String>> deletebyprojectid(@RequestParam Long id)//@RequestBody ProjectsEntity projectsEntity
    {
        return  projectsServiceImplementation.deleteByProjectId(id);
    }

    @GetMapping("project")
    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> getbyid(@RequestParam Long id)
    {
        return projectsServiceImplementation.getbyid(id);
    }


    @PutMapping("project/update")

    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> updatebyid(@RequestParam Long id, @RequestBody ProjectsEntity projectsEntity)
    {
        return projectsServiceImplementation.updatebyid(id,projectsEntity);
    }

    @GetMapping("/pagination")
    public ApiResponseMessage<Page<ProjectsEntity>> getbypagination(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "5") int pageSize)
    {
        return projectsServiceImplementation.findbypagination(offset, pageSize);
    }

    @GetMapping("/project/name")
    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> getByProjectName(@RequestParam String projectName) {
        return projectsServiceImplementation.getProjectByName(projectName);
    }
    @GetMapping("/project/createdby")
    public ResponseEntity<ApiResponseMessage<List<ProjectsEntity>>> getByCreatedBy(@RequestParam String createdBy) {
        return projectsServiceImplementation.getProjectByCreatedBy(createdBy);
    }
    @GetMapping("/project/creationdate")
    public ResponseEntity<ApiResponseMessage<List<ProjectsEntity>>> getByCreatedDate(@RequestParam LocalDate creationDate) {
        return projectsServiceImplementation.getProjectByCreatedDate(creationDate);
    }


}
