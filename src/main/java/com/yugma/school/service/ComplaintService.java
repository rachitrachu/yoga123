package com.yugma.school.service;

import java.util.List;
import java.util.Map;

import com.yugma.school.model.Complaints;

public interface ComplaintService {
	
	public Map<String, Object> saveComplaint(Complaints complaint);
	
	public Map<String, Object> fetchComplaintDetail(long id);
	
	public List<Map<String, Object>> fetchStuComplaint(long id, String type);
	
	public List<Map<String, Object>> fetchManagComplaint(long id, String type);

}
