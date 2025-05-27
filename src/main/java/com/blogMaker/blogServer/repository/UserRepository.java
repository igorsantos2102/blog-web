package com.blogMaker.blogServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogMaker.blogServer.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);
    Optional<User> findByEmail(String email);
}
