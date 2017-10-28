package com.yugma.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name="students", uniqueConstraints={@UniqueConstraint(columnNames="phone")})
@DynamicInsert(value=true)
public class Students {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id")
	private Long student_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="semester")
	private boolean semester;
	
	@ManyToOne
	@JoinColumn(name="yearfk")
	private Year yearfk;
	
	@Transient
	private Long yrfk;
	
	@Column(name="gender")
	private boolean gender;
	
	@Column(name="phone", unique=true, nullable=false)
	private long phone;
	
	@ManyToOne
	@JoinColumn(name="deptfk")
	private Department deptfk;
	
	@Transient
	private Long departmentfk;
	
	@Column(name="password")
	private String password;

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = (Long)student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSemester() {
		return semester;
	}

	public void setSemester(boolean semester) {
		this.semester = semester;
	}

	public Year getYearfk() {
		return yearfk;
	}

	public void setYearfk(Year yearfk) {
		this.yearfk = yearfk;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Department getDeptfk() {
		return deptfk;
	}

	public void setDeptfk(Department deptfk) {
		this.deptfk = deptfk;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder encodePass = new BCryptPasswordEncoder();
		this.password = encodePass.encode(password);
	}

	public Long getDepartmentfk() {
		return departmentfk;
	}

	public void setDepartmentfk(Long departmentfk) {
		if(departmentfk!=null){
			deptfk = new Department();
			deptfk.setDept_id(departmentfk);
		}
		this.departmentfk = departmentfk;
    }

	public Long getYrfk() {
		return yrfk;
	}

	public void setYrfk(Long yrfk) {
		if(yrfk!=null){
			yearfk = new Year();
			yearfk.setId(yrfk);
		}
		this.yrfk = yrfk;
	}

}
