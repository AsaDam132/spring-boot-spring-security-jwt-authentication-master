package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User> followers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User> following = new HashSet<>();

    // Getters and Setters
    //Getter for followers
    public Set<User> getFollowers() {
        return followers;
    }

    // Setter for followers
    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    // Getter for following
    public Set<User> getFollowing() {
        return following;
    }

    // Setter for following
    public void setFollowing(Set<User> following) {
        this.following = following;
    }
}
