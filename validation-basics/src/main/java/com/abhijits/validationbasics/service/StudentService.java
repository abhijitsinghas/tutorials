package com.abhijits.validationbasics.service;

import com.abhijits.validationbasics.domain.StudentDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by   : Abhijit Singh
 * On           : 28 October, 2022
 */
@Service
@Validated
public class StudentService {

    public StudentDto create(@Valid StudentDto studentDto) {
        return studentDto.setUuid(UUID.randomUUID());
    }

}
