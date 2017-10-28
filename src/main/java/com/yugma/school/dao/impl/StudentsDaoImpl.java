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

import com.yugma.school.dao.StudentsDao;
import com.yugma.school.model.Students;


@Repository
public class StudentsDaoImpl extends AbstractDao<Long, Students> implements StudentsDao{
	
	public ProjectionList projectionList(){
		return Projections.projectionList()
				.add(Projections.property("student_id"),"student_id")
				.add(Projections.property("name"),"name")
				.add(Projections.property("gender"),"gender")
				.add(Projections.property("phone"),"phone");
		
	}
	
	public void saveStudents(Students student){
		save(student);
	}
	
	public int deleteStudents(long id){
		return update("delete from Students where student_id ="+id)
				.executeUpdate();
	}
	
	public int updateStudents(Map<String,Object> student){
		if(student.isEmpty() && !student.containsKey("student_id") && student.get("student_id")==null )
			return 0;
		String hql = "update Students set ";
		int count=0;
		
		String name=null;
		
		if(student.containsKey("name") && (String)student.get("name")!=null){
			hql+="name = :name";
			name = student.get("name").toString();
			count++;
		}
		
		if(student.containsKey("semester")&&(String)student.get("semester")!=null){
			if(count>0)
				hql+=",";
			hql+="semester = " +student.get("semester");
			count++;
		}
		
		if(student.containsKey("year")&&(int)student.get("year")!=0){
			if(count>0)
				hql+=",";
			hql+="year = " +student.get("year");
			count++;
		}
		
		if(student.containsKey("gender")&&(String)student.get("gender")!=null){
			if(count>0)
				hql+=",";
			hql+="gender = " +student.get("gender");
			count++;
		}
		
		if(student.containsKey("phone")&&(long)student.get("phone")!=0){
			if(count>0)
				hql+=",";
			hql+="phone = " +student.get("phone");
			count++;
		}
		
		if(student.containsKey("deptfk")&&student.get("deptfk")!=null){
			if(count>0)
				hql+=",";
			hql+="deptfk = " +student.get("deptfk");
			count++;
		}
		
		if(count==0){
			return 0;
		}
		hql+=" where id = "+student.get("student_id");
		
		Query query = update(hql);
		if(name!=null){
			query.setString("name", name);
		}
		return query.executeUpdate();
		
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> fetchStudentById(long id){
		return (Map<String, Object>) createEntityCriteria()
				.add(Restrictions.eq("student_id", id))
				.createAlias("deptfk", "departmentfk", JoinType.INNER_JOIN)
				.createAlias("yearfk", "yrfk",JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("semester"), "semester")
						.add(Projections.property("yrfk.name"), "yrfk")
						.add(Projections.property("departmentfk.name"),"departmentfk")
						.add(projectionList()) )
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> fetchStudentByPhone(long phone){
		return (Map<String, Object>) createEntityCriteria()
				.add(Restrictions.eq("phone", phone))
				.createAlias("deptfk", "departmentfk", JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("semester"), "semester")
						.add(Projections.property("year"), "year")
						.add(Projections.property("departmentfk.id"),"departmentfk")
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
						.add(Projections.property("student_id"),"student"))
				.uniqueResult();	
	}
	
	public int updatePasswordByPhone(Map<String, Object> pass_phone){
		if(pass_phone.isEmpty()||pass_phone.get("phone")==null||pass_phone.get("password")==null)
			return 0;
		String hql="update Students set password = '"+pass_phone.get("password").toString()+"' where phone = "+pass_phone.get("phone").toString();
		Query query=update(hql);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> fetchStudentName(long id){
		return  createEntityCriteria()
				.add(Restrictions.ne("student_id",id ))
				.setProjection(Projections.projectionList()
						.add(Projections.property("name"),"name")
						.add(Projections.property("student_id"),"student_id"))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		}
	
}