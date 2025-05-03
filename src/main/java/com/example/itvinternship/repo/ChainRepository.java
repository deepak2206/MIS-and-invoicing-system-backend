package com.example.itvinternship.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.itvinternship.model.Chain;

@Repository
public interface ChainRepository extends JpaRepository<Chain,Long> {

	boolean existsByGstnNo(String gstNo);
	List<Chain> findByIsActiveTrue();
	List<Chain> findByGroupGroupNameAndIsActiveTrue(String groupName);
}
