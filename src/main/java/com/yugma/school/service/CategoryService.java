package com.yugma.school.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yugma.school.model.Category;

public interface CategoryService {
	
	public void saveCategory(Category category);
	
	public Map<String,Object> fetchCategoryById(long id);
	
	public List<Map<String,Object>> fetchCategoryDetailForComplaint();
	
	public List<Map<String,Object>> fetchCategoryByParentCategoryId(long id);
	
	public Long fetchParentCategoryId(long id);
	
	public int updateCategory(Map<String,Object> category);
	
	public int incrementDepth(long id);
	
	public int incrementDepth(Set<Long> ids);
	
	public int deleteCategory(long id);

}
