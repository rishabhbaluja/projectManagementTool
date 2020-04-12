package com.rishabh.pmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rishabh.pmt.domain.Backlog;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog,Long> {

	Backlog findByProjectIdentifier(String projectIdentifier);
	
}
