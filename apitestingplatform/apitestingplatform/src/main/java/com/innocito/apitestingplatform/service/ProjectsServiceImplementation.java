package com.innocito.apitestingplatform.service;

import com.innocito.apitestingplatform.apiresponse.ApiResponseMessage;
import com.innocito.apitestingplatform.entity.ProjectsEntity;
import com.innocito.apitestingplatform.repository.ProjectsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectsServiceImplementation{

    @Autowired
    private final  ProjectsRepository projectsRepository;


    public ResponseEntity<ApiResponseMessage<String>> saveprojectsentity(ProjectsEntity projectsEntity) {
         projectsRepository.save(projectsEntity);

         ApiResponseMessage<String> responseMessage = new ApiResponseMessage<>("success", "Project added successfully" , null);
         return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    public ApiResponseMessage<List<ProjectsEntity>> getallprojects()
    {
        List<ProjectsEntity> totalprojects = projectsRepository.findAll();
        return new ApiResponseMessage<>("success", "Fetched all registrations", totalprojects);
    }
    public ResponseEntity<ApiResponseMessage<String>> deletebyprojectid(Long id)
    {
        projectsRepository.deleteById(id);

        ApiResponseMessage<String> responseMessage = new ApiResponseMessage<>("success", "Project deleted successfully" , null);
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
            throw new RuntimeException();
        }

    }
    public ResponseEntity<ApiResponseMessage<ProjectsEntity>> updatebyid(Long id, ProjectsEntity updatedProject) {
        ProjectsEntity existingProjectOpt = projectsRepository.findById(id).orElseThrow(() ->new RuntimeException("Project not found")) ;

        existingProjectOpt.setProject_name(updatedProject.getProject_name());
        existingProjectOpt.setDescription(updatedProject.getDescription());
        existingProjectOpt.setType(updatedProject.getType());
        existingProjectOpt.setCreated_by(updatedProject.getCreated_by());

            ProjectsEntity savedProject = projectsRepository.save(existingProjectOpt);
            ApiResponseMessage<ProjectsEntity> responseMessage =
                    new ApiResponseMessage<>("success", "Project updated successfully", savedProject);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
