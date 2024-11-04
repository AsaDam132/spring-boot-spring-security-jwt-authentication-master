package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Tweet;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.TweetRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

@Service
public class TweetServiceImple implements TweetService{

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Tweet createTweet(String content, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Tweet tweet = new Tweet();
        tweet.setContent(content);
        tweet.setUsername(username);

        return tweetRepository.save(tweet);
    }
    @Override
    public Tweet getTweetById(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found"));
    }

    @Override
    public void deleteTweet(Long tweetId, String username) throws AccessDeniedException {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found"));

        if (!tweet.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User not authorized to delete this tweet");
        }
        tweetRepository.delete(tweet);

    }

    @Override
    public List<Tweet> getTweetsByUsername(String username) {

        return tweetRepository.findByUser_Username(username);
    }
    @Override
    public Tweet postTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }
    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }
    @Override
    public Tweet updateTweet(Long id, Tweet updatedTweet) {
        Tweet existingTweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found"));

        existingTweet.setContent(updatedTweet.getContent());
        // Update other fields as necessary

        return tweetRepository.save(existingTweet);
    }
    @Override
    public void deleteTweet(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found"));

        tweetRepository.delete(tweet);
    }
    @Override
    public void likeTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        User currentUser = getCurrentUser();
        tweet.getLikedBy().add(currentUser);
        tweetRepository.save(tweet);
    }
    @Override
    public void unlikeTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        User currentUser = getCurrentUser();
        tweet.getLikedBy().remove(currentUser);
        tweetRepository.save(tweet);
    }
    @Override
    public Set<User> getLikesForTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        return tweet.getLikedBy();
    }
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            // Assuming you have a method in UserRepository to fetch a user by username
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } else {
            throw new RuntimeException("Principal is not an instance of UserDetails");
        }
    }


}
