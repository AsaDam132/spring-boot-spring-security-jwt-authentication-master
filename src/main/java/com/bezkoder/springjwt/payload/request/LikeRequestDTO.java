package com.bezkoder.springjwt.payload.request;

public class LikeRequestDTO {

    private Long tweetId;

    public LikeRequestDTO() {}

    public LikeRequestDTO(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }
}
