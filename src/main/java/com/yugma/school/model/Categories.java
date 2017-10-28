package com.yugma.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name="categories", uniqueConstraints={@UniqueConstraint(columnNames="cat_id")})
@DynamicInsert(value=true)
public class Categories {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cat_id")
	private long cat_id;
	
	@Column(name = "name",nullable=false)
	private String name;
	
	@Column(name="active")
	private boolean active=true;

	public long getCat_id() {
		return cat_id;
	}

	public void setCat_id(long cat_id) {
		this.cat_id = cat_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
