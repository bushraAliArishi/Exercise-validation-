package com.example.event_system.Controller;

import com.example.event_system.ApiResponse.ApiResponse;
import com.example.event_system.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events;

    @GetMapping("/display")
    public ResponseEntity getEvent() {
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @GetMapping("/disply_by_id/{key}")
    public ResponseEntity getEventsbyId(@PathVariable String key) {
        for (Event eventSystem : events) {
            if (eventSystem.getId().equals(key)) {
                return ResponseEntity.status(HttpStatus.OK).body(eventSystem);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("not found");
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("event added"));
    }

    @PutMapping("/update/{key}")
    public ResponseEntity updateEvent(@PathVariable int key, @RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.set(key, event);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("event updated"));
    }

    @DeleteMapping("/delete/{key}")
    public ResponseEntity deleteEvent(@PathVariable int key) {
        events.remove(events.get(key));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("event deleted"));
    }

    @PutMapping("/capacity/{key}/{capacityNum}")
    public ResponseEntity updateEventCapacity(@PathVariable int key, @PathVariable int capacityNum) {

        events.get(key).setCapacity(capacityNum);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("event Capacity updated successfully"));
    }


}
