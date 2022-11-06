package com.abhijits.validationbasics.domain;

import com.abhijits.validationbasics.validator.Uuid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by   : Abhijit Singh
 * On           : 27 October, 2022
 */
public class StudentDto {

    private UUID uuid;

    @NotBlank(message = "Should not be null or empty.")
    private String firstName;

    @NotBlank(message = "Should not be null or empty.")
    private String lastName;

    @NotBlank(message = "Should not be null or empty.")
    @Email(message = "Not valid.")
    private String email;

    @NotBlank(message = "Should not be null or empty.")
    @Size(min = 4, max = 10, message = "Should be min 4 character and max 10 character in length.")
    private String password;

    private String confirmPassword;

    public UUID getUuid() {
        return uuid;
    }

    public StudentDto setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public StudentDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public StudentDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public StudentDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
