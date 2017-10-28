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

@Entity
@Table(name="complaints", uniqueConstraints={@UniqueConstraint(columnNames="comp_id")})
@DynamicInsert(value=true)
public class Complaints {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comp_id")
	private long comp_id;
	
	@ManyToOne
	@JoinColumn(name="statusfk")
	private Status statusfk;
	
	@Transient
	private Long stfk;
	
	@Column(name="title")
	private String title; 
	
	@ManyToOne
	@JoinColumn(name="assignedto")
	private Staff assignedto;
	
	@Transient
	private Long assignfk;
	
	@ManyToOne
	@JoinColumn(name="priorityfk")
	private Priority priorityfk;
	
	@Transient
	private Long prfk;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="by_stuid")
	private Students by_stuid;
	
	@Transient
	private Long bystufk;
	
	@ManyToOne
	@JoinColumn(name="against_stuid")
	private Students against_stuid;
	
	@Transient
	private Long againststufk;
	
	@ManyToOne
	@JoinColumn(name="against_staffid")
	private Staff against_staffid;
	
	@Transient
	private Long againststafffk;
	
	@Column(name="date")
	private String date;
	
	@ManyToOne
	@JoinColumn(name="categoryfk")
	private Categories categoryfk;
	
	@Transient
	private Long catfk;
	
	@Column(name="rca")
	private String rca;
	
	@Column(name="closedon")
	private String closedon;

	public long getComp_id() {
		return comp_id;
	}

	public void setComp_id(long comp_id) {
		this.comp_id = comp_id;
	}

	public Status getStatus() {
		return statusfk;
	}

	public void setStatus(Status statusfk) {
		this.statusfk = statusfk;
	}

	public Staff getAssignedto() {
		return assignedto;
	}

	public void setAssignedto(Staff assignedto) {
		this.assignedto = assignedto;
	}

	public Priority getPriority() {
		return priorityfk;
	}

	public void setPriority(Priority priorityfk) {
		this.priorityfk = priorityfk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Students getBy_stuid() {
		return by_stuid;
	}

	public void setBy_stuid(Students by_stuid) {
		this.by_stuid = by_stuid;
	}

	public Students getAgainst_stuid() {
		return against_stuid;
	}

	public void setAgainst_stuid(Students against_stuid) {
		this.against_stuid = against_stuid;
	}

	public Staff getAgainst_staffid() {
		return against_staffid;
	}

	public void setAgainst_staffid(Staff against_staffid) {
		this.against_staffid = against_staffid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Categories getCategory() {
		return categoryfk;
	}

	public void setCategory(Categories categoryfk) {
		this.categoryfk = categoryfk;
	}

	public Long getCatfk() {
		return catfk;
	}

	public void setCatfk(Long catfk) {
		if(catfk!=null){
			categoryfk = new Categories();
			categoryfk.setCat_id(catfk);
		}
		this.catfk = catfk;
	}

	public Long getStfk() {
		return stfk;
	}

	public void setStfk(Long stfk) {
		if(stfk!=null){
			statusfk = new Status();
			statusfk.setStat_id(stfk);
		}
		this.stfk = stfk;
	}

	public Long getPrfk() {
		return prfk;
	}

	public void setPrfk(Long prfk) {
		if(prfk!=null){
			priorityfk = new Priority();
			priorityfk.setId(prfk);
		}
		this.prfk = prfk;
	}

	public Long getAssignfk() {
		return assignfk;
	}

	public void setAssignfk(Long assignfk) {
		if(assignfk!=null){
			assignedto = new Staff();
			assignedto.setStaff_id(assignfk);
		}
		this.assignfk = assignfk;
	}

	public Long getBystufk() {
		return bystufk;
	}

	public void setBystufk(Long bystufk) {
		if(bystufk!=null){
			by_stuid = new Students();
			by_stuid.setStudent_id(bystufk);
		}
		this.bystufk = bystufk;
	}

	public Long getAgainststufk() {
		return againststufk;
	}

	public void setAgainststufk(Long againststufk) {
		if(againststufk!=null){
			against_stuid = new Students();
			against_stuid.setStudent_id(againststufk);
		}
		this.againststufk = againststufk;
	}

	public Long getAgainststafffk() {
		return againststafffk;
	}

	public void setAgainststafffk(Long againststafffk) {
		if(againststafffk!=null){
			against_staffid = new Staff();
			against_staffid.setStaff_id(againststafffk);
		}
		this.againststafffk = againststafffk;
	}

	public String getRca() {
		return rca;
	}

	public void setRca(String rca) {
		this.rca = rca;
	}

	public String getClosedon() {
		return closedon;
	}

	public void setClosedon(String closedon) {
		this.closedon = closedon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
	
}
