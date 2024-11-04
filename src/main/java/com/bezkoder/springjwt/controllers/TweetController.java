package com.example.twitter.controller;
import com.bezkoder.springjwt.models.Tweet;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.TweetRequestDTO;
import com.bezkoder.springjwt.payload.request.UserDTO;
import com.bezkoder.springjwt.payload.response.LikeResponseDTO;
import com.bezkoder.springjwt.payload.response.TweetResponseDTO;
import com.bezkoder.springjwt.security.services.TweetServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {
    @Autowired
    private TweetServiceImple tweetServiceImple;

    // Get all tweets
    @GetMapping
    public ResponseEntity<List<TweetResponseDTO>> getAllTweets() {
        List<Tweet> tweets = tweetServiceImple.getAllTweets();
        List<TweetResponseDTO> responseDTOs = tweets.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Get tweet by ID
    @GetMapping("/{id}")
    public ResponseEntity<TweetResponseDTO> getTweetById(@PathVariable Long id) {
        Optional<Tweet> tweet = Optional.ofNullable(tweetServiceImple.getTweetById(id));
        return tweet.map(value -> ResponseEntity.ok(mapToResponseDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Post a new tweet
    @PostMapping
    public ResponseEntity<TweetResponseDTO> postTweet(@RequestBody TweetRequestDTO tweetRequestDTO, Authentication authentication) {
        String username = authentication.getName();
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequestDTO.getContent());
        tweet.setUsername(username);
        tweet.setCreatedAt(LocalDateTime.now());

        Tweet savedTweet = tweetServiceImple.postTweet(tweet);
        return ResponseEntity.ok(mapToResponseDTO(savedTweet));
    }

    // Update a tweet by ID
    @PutMapping("/{id}")
    public ResponseEntity<TweetResponseDTO> updateTweet(@PathVariable Long id, @RequestBody TweetRequestDTO updatedTweetDTO, Authentication authentication) {
        Optional<Tweet> tweetOptional = Optional.ofNullable(tweetServiceImple.getTweetById(id));
        if (tweetOptional.isPresent() && tweetOptional.get().getUsername().equals(authentication.getName())) {
            Tweet updatedTweet = new Tweet();
            updatedTweet.setContent(updatedTweetDTO.getContent());
            Tweet savedTweet = tweetServiceImple.updateTweet(id, updatedTweet);
            return ResponseEntity.ok(mapToResponseDTO(savedTweet));
        }
        return ResponseEntity.status(403).build();
    }

    // Delete a tweet by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, Authentication authentication) {
        Optional<Tweet> tweetOptional = Optional.ofNullable(tweetServiceImple.getTweetById(id));
        if (tweetOptional.isPresent() && tweetOptional.get().getUsername().equals(authentication.getName())) {
            tweetServiceImple.deleteTweet(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).build();
    }

    // Helper method to map Tweet to TweetResponseDTO
    private TweetResponseDTO mapToResponseDTO(Tweet tweet) {
        TweetResponseDTO responseDTO = new TweetResponseDTO();
        responseDTO.setId(tweet.getId());
        responseDTO.setContent(tweet.getContent());
        responseDTO.setUsername(tweet.getUsername());
        responseDTO.setCreatedAt(tweet.getCreatedAt());
        return responseDTO;
    }
    @PostMapping("/{tweetId}/like")
    public ResponseEntity<LikeResponseDTO> likeTweet(@PathVariable Long tweetId) {
        tweetServiceImple.likeTweet(tweetId);
        return ResponseEntity.ok(new LikeResponseDTO(tweetId, "success", "Tweet liked successfully."));
    }

    @PostMapping("/{tweetId}/unlike")
    public ResponseEntity<LikeResponseDTO> unlikeTweet(@PathVariable Long tweetId) {
        tweetServiceImple.unlikeTweet(tweetId);
        return ResponseEntity.ok(new LikeResponseDTO(tweetId, "success", "Tweet unliked successfully."));
    }

    @GetMapping("/{tweetId}/likes")
    public ResponseEntity<LikeResponseDTO> getLikesForTweet(@PathVariable Long tweetId) {
        Set<UserDTO> likedUsers = tweetServiceImple.getLikesForTweet(tweetId).stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toSet());
        return ResponseEntity.ok(new LikeResponseDTO(tweetId, likedUsers));
    }
}


