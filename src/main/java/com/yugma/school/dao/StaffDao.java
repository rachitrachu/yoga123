package com.yugma.school.dao;

import java.util.List;
import java.util.Map;

import com.yugma.school.model.Staff;

public interface StaffDao {
	
	public void saveStaff(Staff staff);
	
	public int updateStaff(Map<String,Object> staff);
	
	public int deleteStaff(long id);
	
	public Map<String,Object> fetchStaffById(long id);
	
	public Map<String,Object> fetchStaffByPhone(long phone);
	
	public  Map<String,Object> fetchPasswordByPhone(long phone);
	
	public Long fetchIdByPhone(long phone);
	
	public int updatePasswordByPhone(Map<String,Object> pass_phone);
	
	public List<String> fetchStaffName();


}
