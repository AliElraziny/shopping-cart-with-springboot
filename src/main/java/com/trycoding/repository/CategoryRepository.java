package com.trycoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trycoding.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Boolean existsByName(String name);

}
