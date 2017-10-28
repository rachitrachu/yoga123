package com.yugma.school.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.si.api.SmsSender;
import com.yugma.school.dao.StudentsDao;
import com.yugma.school.model.Students;
import com.yugma.school.service.StudentsService;

@Service("studentsServiceImpl")
@Transactional
public class StudentsServiceImpl implements StudentsService {
	
	@Autowired
	StudentsDao studentsDao;
	
	public void saveStudent(Students student){
		studentsDao.saveStudents(student);
	}

	public int updateStudents(Map<String, Object> student) {
		return studentsDao.updateStudents(student);
	}

	public int deleteStudents(long id) {
		return studentsDao.deleteStudents(id);
	}

	public Map<String, Object> fetchStudentById(long id) {
	    return studentsDao.fetchStudentById(id);
	}

	public Map<String, Object> fetchStudentByPhone(long phone) {
		return studentsDao.fetchStudentByPhone(phone);
	}

	
	public Map<String, Object> fetchPasswordByPhone(long phone) {
		return studentsDao.fetchPasswordByPhone(phone);
	}
	
	public long fetchIdByPhone(long phone, int s){
		Long response= studentsDao.fetchIdByPhone(phone);
		if(response==null||response==0)
			return 0;
		else{
			String otp = UUID.randomUUID().toString().substring(0, 5);
			System.out.println("otp");
			BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
			HashMap<String,Object> mapp= new HashMap<String,Object>();
			mapp.put("phone",phone);
			mapp.put("password", encode.encode(otp));
			int num=studentsDao.updatePasswordByPhone(mapp);
			
			if(num>0&&s==0){
				SmsSender sms = new SmsSender();
				sms.sendSmsNow("8860712977", 
						"otp has been sent : "+otp);
				
			}
			return response;
		}
	}
	
	public List<Map<String, Object>> fetchStudentName(long id){
		return studentsDao.fetchStudentName(id);
	}

}
