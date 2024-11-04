package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.User;

import java.util.List;

public interface FollowerService {

    void followUser(String currentUsername, String targetUsername);
    void unfollowUser(String currentUsername, String targetUsername);
    List<User> getFollowers(String username);
    List<User> getFollowing(String username);
}
