package com.trycoding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trycoding.model.Category;

@Service
public interface CategoryService {
	
	public Category saveCategory(Category category);
	
	public Boolean existCategory(String name);
	
	public List<Category> getAllCategory();
	

}
