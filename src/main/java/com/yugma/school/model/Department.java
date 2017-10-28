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
@Table(name="department", uniqueConstraints={@UniqueConstraint(columnNames="dept_id")})
@DynamicInsert(value=true)
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dept_id")
	private long dept_id;
	
	@Column(name="name")
	private String name;

	public long getDept_id() {
		return dept_id;
	}

	public void setDept_id(long dept_id) {
		this.dept_id = dept_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
