package com.example.itvinternship.service;

import java.util.List;

import com.example.itvinternship.model.Group;

public interface GroupService {
    Group addGroup(String name);
    Group updateGroup(Long id, String newName);
    void deleteGroup(Long id);
    List<Group> getAll();
    Group getById(Long id);
}
