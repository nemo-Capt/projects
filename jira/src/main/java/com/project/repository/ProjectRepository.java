package com.project.repository;

import com.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByName(String name);

}
