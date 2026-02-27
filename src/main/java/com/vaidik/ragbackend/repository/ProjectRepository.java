package com.vaidik.ragbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaidik.ragbackend.entity.Project;
// import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String name);
    Optional<Project> findById(Long id);
    boolean existsByName(String name);
}
