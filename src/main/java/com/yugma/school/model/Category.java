package com.yugma.school.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
@Table(name = "category",uniqueConstraints = 
	{@UniqueConstraint(columnNames = "name")})
@DynamicInsert(value=true)
public class Category implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name",unique = true,nullable=false)
	private String name;

	@ManyToOne
	@JsonBackReference(value = "category-parent-category")
	@JoinColumn(name = "category_id")
	private Category parentCategoryForCategory;

	@Transient
	private Long categoryId;
	
	@Column(name = "depth")
	private int depth=0;
	
	@Transient
	private Long parentDepth;
	
	@Column(name = "active")
	private Boolean active = true;

	@OneToMany(mappedBy = "parentCategoryForCategory", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "category-parent-category")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Category> categoryForParentCategory;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParentCategoryForCategory() {
		return parentCategoryForCategory;
	}

	public void setParentCategoryForCategory(Category parentCategoryForCategory) {
		this.parentCategoryForCategory = parentCategoryForCategory;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Category> getCategoryForParentCategory() {
		return categoryForParentCategory;
	}

	public void setCategoryForParentCategory(List<Category> categoryForParentCategory) {
		this.categoryForParentCategory = categoryForParentCategory;
	}


	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategory_id(Long categoryId) {
		if(categoryId!=null){
			parentCategoryForCategory = new Category();
			parentCategoryForCategory.setId(categoryId);
		}
		this.categoryId = categoryId;
	}

	public Long getParentDepth() {
		return parentDepth;
	}

	public void setParent_depth(Long parentDepth) {
		this.parentDepth = parentDepth;
	}


	public Boolean getActive() {
		return active;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setParentDepth(Long parentDepth) {
		this.parentDepth = parentDepth;
	}
	
}
