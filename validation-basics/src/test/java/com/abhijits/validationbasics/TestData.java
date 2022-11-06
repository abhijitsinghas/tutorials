package com.abhijits.validationbasics;

import com.abhijits.validationbasics.domain.StudentDto;

/**
 * Created by   : Abhijit Singh
 * On           : 28 October, 2022
 */
public class TestData {
    public static  StudentDto invalidInput() {
        return new StudentDto().setPassword("tes").setEmail("email");
    }

    public static StudentDto validInput() {
        return new StudentDto()
                .setFirstName("Thomas")
                .setLastName("Rommel")
                .setEmail("thomas.rommel@gmail.com")
                .setPassword("password")
                .setConfirmPassword("password");
    }
}
