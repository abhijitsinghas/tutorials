package com.abhijits.validationbasics.controller;

import com.abhijits.validationbasics.domain.StudentDto;
import com.abhijits.validationbasics.filter.StudentFilter;
import com.abhijits.validationbasics.validator.Uuid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * Created by   : Abhijit Singh
 * On           : 27 October, 2022
 */
@Validated
@RestController
@RequestMapping("/student")
public class StudentController {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StudentDto> create(@Valid @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentDto.setUuid(UUID.randomUUID()));
    }

    @GetMapping(
            path = "/{uuid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StudentDto> getByUuid(@Uuid @PathVariable String uuid) {
        return ResponseEntity.ok(createStudentDto(UUID.fromString(uuid)));
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StudentDto> get(@Valid StudentFilter studentFilter) {
        return ResponseEntity.ok(createStudentDto(studentFilter.getFirstName(), studentFilter.getLastName()));
    }

    @GetMapping(
            path = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StudentDto> get(@NotBlank(message = "Should not be null or empty.") @Email(message = "Not Valid.") @RequestParam String email) {
        return ResponseEntity.ok(createStudentDto(email));
    }

    private StudentDto createStudentDto(UUID uuid) {
        return createStudentDto(uuid, "Vineet", "Rana", "vineet.rana@gmail.com");
    }

    private StudentDto createStudentDto(String email) {
        return createStudentDto(UUID.randomUUID(), "Vineet", "Rana", email);
    }

    private StudentDto createStudentDto(String firstName, String lastName) {
        return createStudentDto(UUID.randomUUID(), firstName, lastName, "vineet.rana@gmail.com");
    }
    private StudentDto createStudentDto(UUID uuid, String firstName, String lastName, String email) {
        return new StudentDto()
                .setUuid(uuid)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email);
    }

}
