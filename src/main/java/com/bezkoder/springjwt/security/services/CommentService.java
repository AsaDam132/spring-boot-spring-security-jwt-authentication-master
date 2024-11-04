package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Comment;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public interface CommentService {
    Comment addCommentToService(Long tweetId,String content,String username);
    List<org.hibernate.annotations.Comment> getCommentsForTweet(Long tweetId);

    void deleteComment(Long tweetId, Long commentId, String username) throws AccessDeniedException;


}
