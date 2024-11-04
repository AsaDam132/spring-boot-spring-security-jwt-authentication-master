package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Comment;
import com.bezkoder.springjwt.models.Tweet;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.CommentRepository;
import com.bezkoder.springjwt.repository.TweetRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImple implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Comment addCommentToService(Long tweetId, String content, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setTweet(tweet);
        return  null;
//        return commentRepository.save(commen
    }

    @Override
    public List<org.hibernate.annotations.Comment> getCommentsForTweet(Long tweetId) {
        return commentRepository.findTweetId(tweetId);
//                findByTweetId(tweetId);
    }

    @Override
    public void deleteComment(Long tweetId, Long commentId, String username) throws AccessDeniedException {

        Comment comment = (Comment) commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (!comment.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User not authorized to delete this comment");
        }

        commentRepository.delete((org.hibernate.annotations.Comment) comment);
    }
    }

