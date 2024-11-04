package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Tweet;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findTweetId(Long Id);
}
