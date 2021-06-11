package com.example.demo;

import java.awt.Image;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<Design, String> {
	Design findDesignById(Integer id);
	List<Design> findAll();
	long deleteDesignById(Integer id);
}
