package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Followers, Long> {
}
