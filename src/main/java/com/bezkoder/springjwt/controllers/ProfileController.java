package com.bezkoder.springjwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @GetMapping("/all/userprofile")
    public String getUserProfile() {
        return "Public Content.";
    }

}
