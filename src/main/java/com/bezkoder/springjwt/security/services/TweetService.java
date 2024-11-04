package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Tweet;
import com.bezkoder.springjwt.models.User;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

public interface TweetService {
    Tweet createTweet(String content, String username);
    Tweet getTweetById(Long tweetId);
    void deleteTweet(Long tweetId, String username) throws AccessDeniedException;
    List<Tweet> getTweetsByUsername(String username);
    Tweet postTweet(Tweet tweet);
    List<Tweet> getAllTweets();
    Tweet updateTweet(Long id, Tweet updatedTweet);
    void deleteTweet(Long id);
    void likeTweet(Long tweetId);
    void unlikeTweet(Long tweetId);
    Set<User> getLikesForTweet(Long tweetId);
    }


