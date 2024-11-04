package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.security.services.CommentService;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/tweets/{tweetId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment addComment(@PathVariable Long tweetId, @RequestBody String content,
                              @AuthenticationPrincipal String username) {
        return (Comment) commentService.addCommentToService(tweetId,content,username);
//                addCommentToTweet(tweetId, content, username
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable Long tweetId) {
        return commentService.getCommentsForTweet(tweetId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long tweetId, @PathVariable Long commentId,
                              @AuthenticationPrincipal String username) throws AccessDeniedException {
        commentService.deleteComment(tweetId, commentId, username);
    }
}
