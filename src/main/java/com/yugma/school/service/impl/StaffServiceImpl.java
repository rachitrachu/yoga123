package com.yugma.school.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.si.api.SmsSender;
import com.yugma.school.dao.StaffDao;
import com.yugma.school.model.Staff;
import com.yugma.school.service.StaffService;

@Service("staffServiceImpl")
@Transactional
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	StaffDao staffDao;
	
	public void saveStaff(Staff staff){
		staffDao.saveStaff(staff);
	}

	public int updateStaff(Map<String, Object> staff) {
		return staffDao.updateStaff(staff);
	}

	public int deleteStaff(long id) {
		return staffDao.deleteStaff(id);
	}

	public Map<String, Object> fetchStaffById(long id) {
	    return staffDao.fetchStaffById(id);
	}

	public Map<String, Object> fetchStaffByPhone(long phone) {
		return staffDao.fetchStaffByPhone(phone);
	}

	
	public Map<String, Object> fetchPasswordByPhone(long phone) {
		return staffDao.fetchPasswordByPhone(phone);
	}
	
	public long fetchIdByPhone(long phone,int s){
		Long response= staffDao.fetchIdByPhone(phone);
		if(response==null||response==0)
			return 0;
		else{
			String otp = UUID.randomUUID().toString().substring(0, 5);
			BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
			HashMap<String,Object> mapp= new HashMap<String,Object>();
			mapp.put("phone",phone);
			mapp.put("password", encode.encode(otp));
			int num=staffDao.updatePasswordByPhone(mapp);
			
			if(num>0&&s==0){
				SmsSender sms = new SmsSender();
				sms.sendSmsNow(Long.toString(phone), 
						"otp has been sent : "+otp);
				
			}
			return response;
		}
	}
	
	public List<String> fetchStaffName(){
		return staffDao.fetchStaffName();
	}
}
