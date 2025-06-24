package com.innocito.apitestingplatform.controller;


import com.innocito.apitestingplatform.apiresponse.ApiResponseMessage;
import com.innocito.apitestingplatform.entity.ProjectsEntity;
import com.innocito.apitestingplatform.service.ProjectsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectsController {

    @Autowired
    private ProjectsServiceImplementation projectsServiceImplementation;

    @PostMapping("/addproject")
    public ResponseEntity<ApiResponseMessage<String>> saveuserentity(@RequestBody ProjectsEntity projectsEntity) {
       return projectsServiceImplementation.saveprojectsentity(projectsEntity);

    }

    @GetMapping("/allprojects")
    public ResponseEntity<ApiResponseMessage<List<ProjectsEntity>>> getallprojects()
    {
        ApiResponseMessage<List<ProjectsEntity>> response = projectsServiceImplementation.getallprojects();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("project/{id}")
    public ResponseEntity<ApiResponseMessage<String>> deletebyprojectid(@PathVariable("id") Long id)//@RequestBody ProjectsEntity projectsEntity
    {
        return  projectsServiceImplementation.deletebyprojectid(id);
    }

    @GetMapping("project/{id}")
    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> getbyid(@PathVariable("id") Long id)
    {
        return projectsServiceImplementation.getbyid(id);
    }
    @PutMapping("project/{id}")

    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> updatebyid(@PathVariable("id") Long id, @RequestBody ProjectsEntity projectsEntity)
    {
        return projectsServiceImplementation.updatebyid(id,projectsEntity);
    }
}
