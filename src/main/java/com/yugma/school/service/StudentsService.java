package com.yugma.school.service;

import java.util.List;
import java.util.Map;

import com.yugma.school.model.Students;

public interface StudentsService {
	
	public void saveStudent(Students student);
	
	public int updateStudents(Map<String,Object> student);
	
	public int deleteStudents(long id);
	
	public Map<String,Object> fetchStudentById(long id);
	
	public Map<String,Object> fetchStudentByPhone(long phone);
	
	public  Map<String,Object> fetchPasswordByPhone(long phone);
	
	public long fetchIdByPhone(long phone, int s);
	
	public List<Map<String,Object>> fetchStudentName(long id);

}
