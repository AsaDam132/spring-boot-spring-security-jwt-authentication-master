package com.bezkoder.springjwt.payload.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TweetRequestDTO {

    @NotEmpty(message = "Tweet content cannot be empty")
    @Size(max = 280, message = "Tweet content cannot exceed 280 characters")
    private String content;

    // Getters and Setters

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
