package com.itv.invoicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itv.invoicing.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>
{

	Users findByEmail(String email);
	
}
