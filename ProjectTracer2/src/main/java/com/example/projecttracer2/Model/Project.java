package com.example.projecttracer2.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "title is empty")
    @Min(value = 3,message = "the value must contain more than 3 characters ")
    private String id;
    @NotEmpty(message = "title is empty")
    @Min(value = 9,message = "the value must contain more than 9 characters ")
    private String title;
    @NotEmpty(message = "title is empty")
    @Min(value = 15,message = "the value must contain more than 15 characters ")
    private String description;
    @NotEmpty(message = "title is empty")
    @Pattern(regexp = "^(Not Started|in Progress|Completed)$", message = "Status must be 'Not Started', 'in Progress', or 'Completed' only")
    private String status;
    @NotEmpty(message = "title is empty")
    @Min(value = 6,message = "the value must contain more than 6 characters ")
    private String companyName;


}
