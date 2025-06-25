package com.innocito.apitestingplatform.service;

import com.innocito.apitestingplatform.apiresponse.ApiResponseMessage;
import com.innocito.apitestingplatform.entity.ProjectsEntity;
import com.innocito.apitestingplatform.repository.ProjectsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@AllArgsConstructor
public class ProjectsServiceImplementation{

    @Autowired
    private final  ProjectsRepository projectsRepository;


    public ResponseEntity<ApiResponseMessage<String>> saveprojectsentity(ProjectsEntity projectsEntity)
    {
         projectsRepository.save(projectsEntity);

         ApiResponseMessage<String> responseMessage = new ApiResponseMessage<>("success", "Project added successfully" , null);
         return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }


    public ApiResponseMessage<List<ProjectsEntity>> getallprojects()
    {
        List<ProjectsEntity> totalprojects = projectsRepository.findAll();
        return new ApiResponseMessage<>("success", "Fetched all Projects Successfully", totalprojects);
    }

    public ResponseEntity<ApiResponseMessage<String>> deleteByProjectId(Long id) {
        if (!projectsRepository.existsById(id)) {
            ApiResponseMessage<String> responseMessage = new ApiResponseMessage<>(
                    "error",
                    "Project with ID " + id + " does not exist",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }

        projectsRepository.deleteById(id);

        ApiResponseMessage<String> responseMessage = new ApiResponseMessage<>(
                "success",
                "Project deleted successfully",
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }



    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> getbyid(Long id)
    {
        Optional<ProjectsEntity> optionalUser = projectsRepository.findById(id);

        if(optionalUser.isPresent())
        {
        ApiResponseMessage<ProjectsEntity> responseMessage = new ApiResponseMessage<>("success", "Project Found successfully" , optionalUser.get());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseMessage);
        }
        else
        {
            ApiResponseMessage<ProjectsEntity> responseMessage = new ApiResponseMessage<>("Failure", "Project Not Existed" , null);
            return ResponseEntity.status(HttpStatus.FOUND).body(responseMessage);
        }

    }


    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> updatebyid(Long id, ProjectsEntity updatedProject)
    {

        if (!projectsRepository.existsById(id)) {
            ApiResponseMessage<ProjectsEntity> responseMessage = new ApiResponseMessage<>(
                    "error",
                    "Project with ID " + id + " does not exist",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
        ProjectsEntity existingProjectOpt = projectsRepository.findById(id).orElseThrow(() ->new RuntimeException("Project not found")) ;

        existingProjectOpt.setProjectName(updatedProject.getProjectName());
        existingProjectOpt.setDescription(updatedProject.getDescription());
        existingProjectOpt.setType(updatedProject.getType());
        existingProjectOpt.setCreatedBy(updatedProject.getCreatedBy());

            ProjectsEntity savedProject = projectsRepository.save(existingProjectOpt);
            ApiResponseMessage<ProjectsEntity> responseMessage =
                    new ApiResponseMessage<>("success", "Project updated successfully", savedProject);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }


    public ApiResponseMessage<Page<ProjectsEntity>> findbypagination(int offset, int pageSize)
    {
        Page<ProjectsEntity> projects = projectsRepository.findAll(PageRequest.of(offset,pageSize));
        return new ApiResponseMessage<>("success", "Fetched all Projects by given pagination ", projects);

    }


    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> getProjectByName(String projectName) {
        Optional<ProjectsEntity> projectOpt = projectsRepository.findByProjectName(projectName);

        if (projectOpt.isPresent()) {
            ApiResponseMessage<ProjectsEntity> responseMessage = new ApiResponseMessage<>("success", "Project found", projectOpt.get());
            return ResponseEntity.status(HttpStatus.FOUND).body(responseMessage);
        } else {
            ApiResponseMessage<ProjectsEntity> responseMessage = new ApiResponseMessage<>("failure", "Project not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
    }



    public ResponseEntity<ApiResponseMessage<List<ProjectsEntity>>> getProjectByCreatedBy(String createdBy) {
        List<ProjectsEntity> projects = projectsRepository.findByCreatedByContainingIgnoreCase(createdBy);

        if (!projects.isEmpty()) {
            ApiResponseMessage<List<ProjectsEntity>> responseMessage = new ApiResponseMessage<>("success", "Project found", projects);
            return ResponseEntity.status(HttpStatus.FOUND).body(responseMessage);
        } else {
            ApiResponseMessage<List<ProjectsEntity>> responseMessage = new ApiResponseMessage<>("failure", "Project not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
    }


    public ResponseEntity<ApiResponseMessage<List<ProjectsEntity>>> getProjectByCreatedDate(LocalDate creationDate) {
        try {
            List<ProjectsEntity> projectsList = projectsRepository.findByCreationDate(creationDate);

            if (!projectsList.isEmpty()) {
                ApiResponseMessage<List<ProjectsEntity>> responseMessage = new ApiResponseMessage<>("success", "Projects found", projectsList);
                return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            } else {
                ApiResponseMessage<List<ProjectsEntity>> responseMessage = new ApiResponseMessage<>("failure", "No projects found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
            }

        } catch (DateTimeException e) {
            ApiResponseMessage<List<ProjectsEntity>> responseMessage =
                    new ApiResponseMessage<>("Failure", "Invalid Input or Format", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
    }


}
