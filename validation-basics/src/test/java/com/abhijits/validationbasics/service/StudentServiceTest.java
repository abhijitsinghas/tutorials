package com.abhijits.validationbasics.service;

import com.abhijits.validationbasics.domain.StudentDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static com.abhijits.validationbasics.TestData.invalidInput;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by   : Abhijit Singh
 * On           : 28 October, 2022
 */
@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void test_create_in_valid_input() {

        StudentDto invalidInput = invalidInput();

        try {
            studentService.create(invalidInput);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(ConstraintViolationException.class);
            ConstraintViolationException ex = (ConstraintViolationException) e;

            assertThat(ex.getConstraintViolations()).extracting("message").containsExactlyInAnyOrder(
                            "Should not be null or empty.",
                            "Should not be null or empty.",
                            "Not valid.",
                            "Should be min 4 character and max 10 character in length."
            );

        }


    }

}