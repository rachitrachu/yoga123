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
@Table(name="staff", uniqueConstraints={@UniqueConstraint(columnNames="staff_id")})
@DynamicInsert(value=true)
public class Staff {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="staff_id")
	private long staff_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone", unique=true, nullable=false)
	private long phone;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="staffdeptfk")
	private Department staffdeptfk;
	
	@Transient
	private Long staffdepartmentfk;

	public long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getStaffdeptfk() {
		return staffdeptfk;
	}

	public void setStaffdeptfk(Department staffdeptfk) {
		this.staffdeptfk = staffdeptfk;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder encodePass = new BCryptPasswordEncoder();
		this.password = encodePass.encode(password);
	}

	public Long getStaffdepartmentfk() {
		return staffdepartmentfk;
	}

	public void setStaffdepartmentfk(Long staffdepartmentfk) {
		if(staffdepartmentfk!=null){
			staffdeptfk = new Department();
			staffdeptfk.setDept_id(staffdepartmentfk);
		}
		this.staffdepartmentfk = staffdepartmentfk;
	}

	

}
