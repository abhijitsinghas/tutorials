package com.abhijits.validationbasics.filter;

import javax.validation.constraints.NotBlank;

/**
 * Created by   : Abhijit Singh
 * On           : 28 October, 2022
 */
public class StudentFilter {

    @NotBlank(message = "Should not be null or empty.")
    private String firstName;

    @NotBlank(message = "Should not be null or empty.")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public StudentFilter setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentFilter setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
