package com.example.projecttracer2.Controller;

import com.example.projecttracer2.ApiResponse.ApiResponse;
import com.example.projecttracer2.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects;

    @GetMapping("/display")
    public ResponseEntity getProjects() {

        return new ResponseEntity(projects, HttpStatus.OK);

    }

    @GetMapping("/search/{key}")
    public ResponseEntity getProjectSearch(@PathVariable String key) {
        for (Project projectTracker : projects) {
            if (projectTracker.getTitle().equals(key)) {
                return ResponseEntity.status(HttpStatus.OK).body(projectTracker);
            }

        }
        return ResponseEntity.status(HttpStatus.OK).body("not found");
    }

    @GetMapping("/company_projects/{company_name}")
    public ResponseEntity getComProjectTrackers(@PathVariable String company_name) {
        ArrayList<Project> projectTrackersComp = new ArrayList<>();

        for (Project projectTracker : projects) {
            if (projectTracker.getCompanyName().equals(company_name)) {
            }
            projectTrackersComp.add(projectTracker);
        }
        return ResponseEntity.status(HttpStatus.OK).body("the company (" + company_name + ") has thees projects " + projectTrackersComp);
    }

    @PostMapping("/addproject")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Project added successfully"));

    }

    @PutMapping("/update/{key}")
    public ResponseEntity updateProject(@PathVariable int key, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        projects.set(key, project);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Project updated successfully"));
    }

    @PutMapping("/updatestatus/{key}/{status}")
    public ResponseEntity updateProjectstatus(@PathVariable int key) {
        if (projects.get(key).getStatus().equals("Not Started")) {
            projects.get(key).setStatus("in Progress");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Project status updated to in Progress successfully"));
        } else {
            projects.get(key).setStatus("Completed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Project status updated to Completed successfully"));
        }
    }

    @DeleteMapping("/delete/{key}")
    public ResponseEntity updateProject(@PathVariable int key) {

        projects.remove(projects.get(key));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Project deleted successfully"));
    }


}
