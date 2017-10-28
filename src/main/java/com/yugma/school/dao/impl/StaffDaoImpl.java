package com.yugma.school.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.yugma.school.dao.StaffDao;
import com.yugma.school.model.Staff;

@Repository
public class StaffDaoImpl extends AbstractDao<Long, Staff> implements StaffDao {
	
	public ProjectionList projectionList(){
		return Projections.projectionList()
				.add(Projections.property("staff_id"),"staff_id")
				.add(Projections.property("name"),"name")
				.add(Projections.property("phone"),"phone");
		
	}
	
	public void saveStaff(Staff staff){
		save(staff);
	}
	
	public int deleteStaff(long id){
		return update("delete from Staff where staff_id ="+id)
				.executeUpdate();
	}
	
	public int updateStaff(Map<String,Object> staff){
		if(staff.isEmpty() && !staff.containsKey("staff_id") && staff.get("staff_id")==null )
			return 0;
		String hql = "update Staff set ";
		int count=0;
		
		String name=null;
		
		if(staff.containsKey("name") && (String)staff.get("name")!=null){
			hql+="name = :name";
			name = staff.get("name").toString();
			count++;
		}
		
		if(staff.containsKey("phone")&&(long)staff.get("phone")!=0){
			if(count>0)
				hql+=",";
			hql+="phone = " +staff.get("phone");
			count++;
		}
		
		if(staff.containsKey("staffdeptfk")&&staff.get("staffdeptfk")!=null){
			if(count>0)
				hql+=",";
			hql+="staffdeptfk = " +staff.get("staffdeptfk");
			count++;
		}
		
		if(count==0){
			return 0;
		}
		hql+=" where id = "+staff.get("student_id");
		
		Query query = update(hql);
		if(name!=null){
			query.setString("name", name);
		}
		return query.executeUpdate();
		
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> fetchStaffById(long id){
		return (Map<String, Object>) createEntityCriteria()
				.add(Restrictions.eq("staff_id", id))
				.createAlias("staffdeptfk", "staffdepartmentfk", JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("staffdepartmentfk.id"),"staffdepartmentfk")
						.add(projectionList()) )
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> fetchStaffByPhone(long phone){
		return (Map<String, Object>) createEntityCriteria()
				.add(Restrictions.eq("phone", phone))
				.createAlias("staffdeptfk", "staffdepartmentfk", JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("staffdepartmentfk.name"),"staffdepartmentfk")
						.add(projectionList()) )
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public  Map<String,Object> fetchPasswordByPhone(long phone){
		return (Map<String,Object>) createEntityCriteria()
				.add(Restrictions.eq("phone",phone))
				.setProjection(Projections.projectionList()
				.add(Projections.property("password"),"password"))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
	}
	
	public  Long fetchIdByPhone(long phone){
		return (Long) createEntityCriteria()
				.add(Restrictions.eq("phone",phone))
				.setProjection(Projections.projectionList()
						.add(Projections.property("staff_id"),"staff_id"))
				.uniqueResult();	
	}
	
	public int updatePasswordByPhone(Map<String, Object> pass_phone){
		if(pass_phone.isEmpty()||pass_phone.get("phone")==null||pass_phone.get("password")==null)
			return 0;
		String hql="update Staff set password = '"+pass_phone.get("password").toString()+"' where phone = "+pass_phone.get("phone").toString();
		Query query=update(hql);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> fetchStaffName(){
		return  createEntityCriteria()
				.setProjection(Projections.projectionList()
						.add(Projections.property("name"),"name"))
				.list();
		}

}
