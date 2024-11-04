package com.bezkoder.springjwt.payload.response;

import com.bezkoder.springjwt.payload.request.UserDTO;

import java.util.Set;

public class LikeResponseDTO {
    private Long tweetId;
    private String status;

    private Set<UserDTO> likedBy;

    private String message;

    public LikeResponseDTO() {}

    public LikeResponseDTO(Long tweetId, String status, String message) {
        this.tweetId = tweetId;
        this.status = status;
        this.message = message;
    }
    public LikeResponseDTO(Long tweetId, Set<UserDTO> likedBy) {
        this.tweetId = tweetId;
        this.likedBy = likedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Set<UserDTO> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<UserDTO> likedBy) {
        this.likedBy = likedBy;
    }
}
