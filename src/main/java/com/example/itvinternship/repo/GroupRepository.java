package com.example.itvinternship.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itvinternship.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByGroupName(String groupName);
    boolean existsByGroupName(String groupName);
}
