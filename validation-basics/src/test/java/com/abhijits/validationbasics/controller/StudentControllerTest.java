package com.abhijits.validationbasics.controller;

import com.abhijits.validationbasics.domain.StudentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static com.abhijits.validationbasics.TestData.invalidInput;
import static com.abhijits.validationbasics.TestData.validInput;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by   : Abhijit Singh
 * On           : 28 October, 2022
 */
@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_post_request_body_ok() throws Exception {
        StudentDto input = validInput();
        String body = objectMapper.writeValueAsString(input);

        ResultActions response = mvc.perform(post("/student")
                                            .contentType("application/json")
                                            .content(body));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(input.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(input.getLastName()))
                .andExpect(jsonPath("$.email").value(input.getEmail()))
                .andExpect(jsonPath("$.password").value(input.getPassword()))
                .andExpect(jsonPath("$.confirmPassword").value(input.getConfirmPassword()));

    }

    @Test
    public void test_post_request_body_bad_request() throws Exception {
        StudentDto input = invalidInput();
        String body = objectMapper.writeValueAsString(input);

        ResultActions response = mvc.perform(post("/student")
                .contentType("application/json")
                .content(body));

        System.out.println(body);

        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsInAnyOrder(
                        "firstName : Should not be null or empty.",
                        "lastName : Should not be null or empty.",
                        "email : Not valid.",
                        "password : Should be min 4 character and max 10 character in length."
                )));
    }

    @Test
    public void test_get_path_variable_ok() throws Exception {
        UUID uuid = UUID.randomUUID();

        ResultActions response = mvc.perform(get("/student/{uuid}", uuid )
                .contentType("application/json"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()));
    }

    @Test
    public void test_get_path_variable_bad_request() throws Exception {
        String inValidUuid = "vineet.com";

        ResultActions response = mvc.perform(get("/student/{email}", inValidUuid )
                .contentType("application/json"));

        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.contains(
                        "getByUuid.uuid : '"+ inValidUuid + "' is not a valid uuid."
                )));
    }

    @Test
    public void test_get_query_parameter_bean_ok() throws Exception {
        String firstName = "Vineet";
        String lastName = "Rana";

        ResultActions response = mvc.perform(get("/student" )
                .contentType("application/json")
                .param("firstName", firstName)
                .param("lastName", lastName)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName));
    }

    @Test
    public void test_get_query_parameter_bean_bad_request() throws Exception {
        String firstName = "";
        String lastName = "";

        ResultActions response = mvc.perform(get("/student" )
                .contentType("application/json")
                .param("firstName", firstName)
                .param("lastName", lastName)
        );

        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsInAnyOrder(
                        "firstName : Should not be null or empty.",
                        "lastName : Should not be null or empty."
                )));
    }

    @Test
    public void test_get_query_parameter_ok() throws Exception {
        String email = "vineet.rana@gmail.com";

        ResultActions response = mvc.perform(get("/student/search")
                .contentType("application/json")
                .param("email", email)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    public void test_get_query_parameter_bad_request() throws Exception {
        String email = "vineet.com";

        ResultActions response = mvc.perform(get("/student/search" )
                .contentType("application/json")
                .param("email", email)
        );

        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsInAnyOrder(
                        "get.email : Not Valid."
                )));
    }

    //e4d60c03-12ec-4815-9df0-9b9a8a9a782b

}