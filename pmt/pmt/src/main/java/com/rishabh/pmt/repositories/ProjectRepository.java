package com.rishabh.pmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabh.pmt.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
