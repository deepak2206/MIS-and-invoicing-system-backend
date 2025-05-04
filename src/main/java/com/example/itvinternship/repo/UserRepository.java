package com.example.itvinternship.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itvinternship.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
