package com.yugma.school.dao;

import java.util.List;
import java.util.Map;

import com.yugma.school.model.Students;

public interface StudentsDao {
	
	public void saveStudents(Students student);
	
	public int updateStudents(Map<String,Object> student);
	
	public int deleteStudents(long id);
	
	public Map<String,Object> fetchStudentById(long id);
	
	public Map<String,Object> fetchStudentByPhone(long phone);
	
	public  Map<String,Object> fetchPasswordByPhone(long phone);
	
	public Long fetchIdByPhone(long phone);
	
	public int updatePasswordByPhone(Map<String,Object> pass_phone);
	
	public List<Map<String,Object>> fetchStudentName(long id);

}
