package com.example.event_system.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty
    @Min(value = 3, message = "the value must be more than 3 characters")
    private String id;
    @NotEmpty
    @Min(value = 15, message = "the value must be more than 15 characters")
    private String description;
    @NotNull
    @Min(value = 25, message = "Value must be greater than or equal to 25")
    private int capacity;
    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDateTime endDate;

}
