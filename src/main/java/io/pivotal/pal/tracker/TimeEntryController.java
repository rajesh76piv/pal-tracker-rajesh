package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository repository) {
        this.timeEntryRepository = repository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntryRepository.create(timeEntryToCreate));
    }
    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
       TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
       return timeEntry != null ?
        ResponseEntity.ok().body(timeEntry) :
               ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok().body(timeEntryRepository.list());
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable  long id, @RequestBody TimeEntry expected) {

        TimeEntry timeEntry = timeEntryRepository.update(id, expected);
        return timeEntry != null ?
                ResponseEntity.ok().body(timeEntry) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);


    }
}
