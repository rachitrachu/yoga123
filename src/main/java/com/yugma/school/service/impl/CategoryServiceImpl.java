package com.yugma.school.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yugma.school.dao.CategoryDao;
import com.yugma.school.model.Category;
import com.yugma.school.service.CategoryService;

@Service("categoryServiceImpl")
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categoryDao;
	
	public void saveCategory(Category category){
		categoryDao.saveCategory(category);
	}
	
	public Map<String,Object> fetchCategoryById(long id){
		return categoryDao.fetchCategoryById(id);
	}
	
	public List<Map<String,Object>> fetchCategoryDetailForComplaint(){
		List<Map<String,Object>> parentCategories = categoryDao.fetchParentCategory();
		for(Map<String,Object> parentCategory : parentCategories){
			if((int)parentCategory.get("depth")!=0 || 
					parentCategory.get("name").toString().toLowerCase().equals("teacher")){
				parentCategory.put("childCategory", 
						categoryDao.fetchCategoryByParentCategoryId(
								Long.parseLong(parentCategory.get("id").toString())));
			}
		}
		return parentCategories;
	}
	
	public List<Map<String,Object>> fetchCategoryByParentCategoryId(long id){
		return categoryDao.fetchCategoryByParentCategoryId(id);
	}
	
	public Long fetchParentCategoryId(long id){
		return categoryDao.fetchParentCategoryId(id);
	}
	
	public int updateCategory(Map<String,Object> category){
		return categoryDao.updateCategory(category);
	}
	
	public int incrementDepth(long id){
		return categoryDao.incrementDepth(id);
	}
	
	public int incrementDepth(Set<Long> ids){
		return categoryDao.incrementDepth(ids);
	}
	
	public int deleteCategory(long id){
		return categoryDao.deleteCategory(id);
	}
}
