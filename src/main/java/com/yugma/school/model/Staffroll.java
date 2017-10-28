package com.yugma.school.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="staffrole", uniqueConstraints={@UniqueConstraint(columnNames="sr_id")})
public class Staffroll {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sr_id")
	private long sr_id;
	
	@ManyToOne
	@JoinColumn(name="staffidfk")
	private Staff staffidfk;
	
	@ManyToOne
	@JoinColumn(name="roleidfk")
	private Role roleidfk;
	
	@ManyToOne
	@JoinColumn(name="parent_role")
	private Staffroll parent_role;

	public long getSr_id() {
		return sr_id;
	}

	public void setSr_id(long sr_id) {
		this.sr_id = sr_id;
	}

	public Staff getStaffidfk() {
		return staffidfk;
	}

	public void setStaffidfk(Staff staffidfk) {
		this.staffidfk = staffidfk;
	}

	public Role getRoleidfk() {
		return roleidfk;
	}

	public void setRoleidfk(Role roleidfk) {
		this.roleidfk = roleidfk;
	}

	public Staffroll getParent_role() {
		return parent_role;
	}

	public void setParent_role(Staffroll parent_role) {
		this.parent_role = parent_role;
	}

}
