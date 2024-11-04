package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FollowerController {
    @Autowired
    private FollowerService followerService;
    @PostMapping("/{username}/follow")
    public void followUser(@AuthenticationPrincipal String currentUsername, @PathVariable String username) {
        followerService.followUser(currentUsername, username);
    }
    @PostMapping("/{username}/unfollow")
    public void unfollowUser(@AuthenticationPrincipal String currentUsername, @PathVariable String username) {
        followerService.unfollowUser(currentUsername, username);
    }
    @GetMapping("/{username}/followers")
    public List<User> getFollowers(@PathVariable String username) {
        return followerService.getFollowers(username);
    }
    @GetMapping("/{username}/following")
    public List<User> getFollowing(@PathVariable String username) {
        return followerService.getFollowing(username);
    }
}
