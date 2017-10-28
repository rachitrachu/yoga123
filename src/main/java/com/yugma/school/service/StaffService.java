package com.yugma.school.service;

import java.util.List;
import java.util.Map;

import com.yugma.school.model.Staff;

public interface StaffService {
	
	public void saveStaff(Staff staff);
	
	public int updateStaff(Map<String,Object> staff);
	
	public int deleteStaff(long id);
	
	public Map<String,Object> fetchStaffById(long id);
	
	public Map<String,Object> fetchStaffByPhone(long phone);
	
	public  Map<String,Object> fetchPasswordByPhone(long phone);
	
	public long fetchIdByPhone(long phone, int s);
	
	public List<String> fetchStaffName();

}
