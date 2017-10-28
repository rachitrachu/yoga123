package com.yugma.school.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yugma.school.dao.ComplaintDao;
import com.yugma.school.model.Complaints;
import com.yugma.school.service.ComplaintService;

@Service("complaintServiceImpl")
@Transactional
public class ComplaintServiceImpl implements ComplaintService{
	
	@Autowired
	ComplaintDao complaintDao;
	
	public Map<String, Object> saveComplaint(Complaints complaint){
		Map<String, Object> hello;
			   complaintDao.saveComplaint(complaint);
		hello=fetchComplaintDetail(complaint.getComp_id());
		return hello;
	}
	
	public Map<String, Object> fetchComplaintDetail(long id){
		return complaintDao.fetchComplaintDetail(id);
	}
	
	public List<Map<String, Object>> fetchStuComplaint(long id, String type){
		List<Map<String, Object>> response=null;
		
		if(type.equals("open"))
			response= complaintDao.fetchStudentOpenComplaint(id);
		
		if(type.equals("close"))
			response= complaintDao.fetchStudentCloseComplaint(id);
		
		return response;
	}
	
	public List<Map<String, Object>> fetchManagComplaint(long id, String type){
		List<Map<String, Object>> response=null;
		
		if(type.equals("open"))
			response=complaintDao.fetchManagOpenComplaint(id);
		
		if(type.equals("close"))
			response=complaintDao.fetchManagCloseComplaint(id);
		
		return response;
	}

}
