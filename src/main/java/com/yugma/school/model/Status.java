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
@Table(name="status", uniqueConstraints={@UniqueConstraint(columnNames="stat_id")})
@DynamicInsert(value=true)
public class Status {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="stat_id")
	private long stat_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="active")
	private boolean active=true;

	public long getStat_id() {
		return stat_id;
	}

	public void setStat_id(long stat_id) {
		this.stat_id = stat_id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
