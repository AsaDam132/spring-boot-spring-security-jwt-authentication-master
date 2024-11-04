package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import com.bezkoder.springjwt.models.Comment;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;
    // Getter and Setter for id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        this.content = content;
    }

    // Getter and Setter for createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        if (createdAt == null) {
            throw new IllegalArgumentException("Creation time cannot be null");
        }
        this.createdAt = createdAt;
    }

    // Getter and Setter for user (Many-to-One relationship with User)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = user;
    }

    // Getter and Setter for tweet (Many-to-One relationship with Tweet)
    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        if (tweet == null) {
            throw new IllegalArgumentException("Tweet cannot be null");
        }
        this.tweet = tweet;
    }
}
