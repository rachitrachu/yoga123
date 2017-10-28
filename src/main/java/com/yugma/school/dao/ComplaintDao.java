package com.yugma.school.dao;

import java.util.List;
import java.util.Map;

import com.yugma.school.model.Complaints;

public interface ComplaintDao {
	
	public void saveComplaint(Complaints complaint);
	
	public int updateComplaint(Map<String,Object> complaint);
	
	public int deleteComplaint(long id);
	
	public List<Map<String, Object>> fetchStudentOpenComplaint(Long id);
	
	public List<Map<String, Object>>  fetchStudentCloseComplaint(Long id);
	
	public List<Map<String, Object>> fetchManagOpenComplaint(Long id);
	
	public List<Map<String, Object>> fetchManagCloseComplaint(Long id);
	
	public Map<String, Object> fetchComplaintDetail(long id);

}
