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

import com.yugma.school.dao.ComplaintDao;
import com.yugma.school.model.Complaints;

@Repository
public class ComplaintDaoImpl extends AbstractDao<Long, Complaints> implements ComplaintDao{

	public ProjectionList projectionList(){
		return Projections.projectionList()
				.add(Projections.property("comp_id"),"comp_id")
				.add(Projections.property("statusfk.name"),"statusfk")
				.add(Projections.property("assignedto.name"),"assignedto")
				.add(Projections.property("description"),"description")
				.add(Projections.property("date"),"date")
				.add(Projections.property("categoryfk.name"),"categoryfk");
		
	}
	
	public void saveComplaint(Complaints complaint){
		save(complaint);
	}
	
	public int deleteComplaint(long id){
		return update("delete from Complaints where comp_id ="+id)
				.executeUpdate();
	}
	
	public int updateComplaint(Map<String,Object> complaint){
		if(complaint.isEmpty() && !complaint.containsKey("comp_id") && complaint.get("comp_id")==null )
			return 0;
		String hql = "update Complaints set ";
		int count=0;
		
		String description=null, rca=null, closedon=null;
		
		if(complaint.containsKey("statusfk") && (String)complaint.get("statusfk")!=null){
			hql+="statusfk"+complaint.get("statusfk");
			count++;
		}
		
		if(complaint.containsKey("assignedto")&&(String)complaint.get("addignedto")!=null){
			if(count>0)
				hql+=",";
			hql+="assignedto = " +complaint.get("assignedto");
			count++;
		}
		
		if(complaint.containsKey("priorityfk")&&(int)complaint.get("priorityfk")!=0){
			if(count>0)
				hql+=",";
			hql+="priorityfk = " +complaint.get("priorityfk");
			count++;
		}
		
		if(complaint.containsKey("against_stuid")&&(String)complaint.get("against_stuid")!=null){
			if(count>0)
				hql+=",";
			hql+="against_stuid = " +complaint.get("against_stuid");
			count++;
		}
		
		if(complaint.containsKey("against_staffid")&&(long)complaint.get("against_staffid")!=0){
			if(count>0)
				hql+=",";
			hql+="against_staffid = " +complaint.get("against_staffid");
			count++;
		}
		
		if(complaint.containsKey("categoryfk")&&complaint.get("categoryfk")!=null){
			if(count>0)
				hql+=",";
			hql+="categoryfk = " +complaint.get("categoryfk");
			count++;
		}
		
		if(complaint.containsKey("description") && (String)complaint.get("decription")!=null){
			hql+="description = :description";
			description = complaint.get("description").toString();
			count++;
		}
		
		if(complaint.containsKey("rca") && (String)complaint.get("rca")!=null){
			hql+="rca = :rca";
			rca = complaint.get("rca").toString();
			count++;
		}
		
		if(complaint.containsKey("closedon") && (String)complaint.get("closedon")!=null){
			hql+="closedon = :closedon";
			closedon = complaint.get("closedon").toString();
			count++;
		}
		
		if(count==0){
			return 0;
		}
		hql+=" where id = "+complaint.get("comp_id");
		
		Query query = update(hql);
		if(description!=null){
			query.setString("description", description);
		}
		
		if(rca!=null){
			query.setString("rca", rca);
		}
		
		if(closedon!=null){
			query.setString("closedon", closedon);
		}
		
		return query.executeUpdate();
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> fetchStudentOpenComplaint(Long id){
		return  createEntityCriteria()
				.createAlias("by_stuid", "by_stu",JoinType.INNER_JOIN)
				.createAlias("statusfk", "status", JoinType.INNER_JOIN)
				.add(Restrictions.eq("by_stu.id",id ))
				.add(Restrictions.ne("status.id",Long.valueOf(5)))
				.createAlias("assignedto", "assignedto", JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("status.name"),"status")
						.add(Projections.property("assignedto.name"),"assignedto")
						.add(Projections.property("date"),"date")
						.add(Projections.property("title"),"title")
						.add(Projections.property("comp_id"),"comp_id"))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> fetchStudentCloseComplaint(Long id){
		return  createEntityCriteria()
				.createAlias("by_stuid", "by_stu",JoinType.INNER_JOIN)
				.createAlias("statusfk", "status", JoinType.INNER_JOIN)
				.add(Restrictions.eq("by_stu.id",id ))
				.add(Restrictions.eq("status.id",Long.valueOf(5)))
				.createAlias("against_stuid", "againsts", JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("status.name"),"status")
						.add(Projections.property("againsts.name"),"againsts")
						.add(Projections.property("date"),"date")
						.add(Projections.property("comp_id"),"comp_id"))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> fetchManagOpenComplaint(Long id){
		return  createEntityCriteria()
				.createAlias("assignedto", "assignedto",JoinType.INNER_JOIN)
				.createAlias("statusfk", "status", JoinType.INNER_JOIN)
				.add(Restrictions.eq("assignedto.id",id ))
				.add(Restrictions.ne("status.id",Long.valueOf(5)))
				.createAlias("against_stuid", "againsts", JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("status.name"),"status")
						.add(Projections.property("againsts.name"),"againsts")
						.add(Projections.property("date"),"date")
						.add(Projections.property("comp_id"),"comp_id"))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> fetchManagCloseComplaint(Long id){
		return  createEntityCriteria()
				.createAlias("assignedto", "assignedto",JoinType.INNER_JOIN)
				.createAlias("statusfk", "status", JoinType.INNER_JOIN)
				.add(Restrictions.eq("assignedto.id",id ))
				.add(Restrictions.eq("status.id",Long.valueOf(5)))
				.createAlias("against_stuid", "againsts", JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("status.name"),"status")
						.add(Projections.property("againsts.name"),"againsts")
						.add(Projections.property("date"),"date")
						.add(Projections.property("comp_id"),"comp_id"))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> fetchComplaintDetail(long id){
		return (Map<String, Object>) createEntityCriteria()
				.add(Restrictions.eq("comp_id", id))
				.createAlias("against_stuid", "against_stuid", JoinType.INNER_JOIN)
				.createAlias("by_stuid", "by_stuid",JoinType.LEFT_OUTER_JOIN)
				.createAlias("priorityfk", "priorityfk",JoinType.LEFT_OUTER_JOIN)
				.createAlias("statusfk", "statusfk",JoinType.LEFT_OUTER_JOIN)
				.createAlias("assignedto", "assignedto",JoinType.LEFT_OUTER_JOIN)
				.createAlias("categoryfk", "categoryfk",JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.property("against_stuid.name"), "against_stuid")
						.add(Projections.property("by_stuid.name"), "by_stuid")
						.add(Projections.property("priorityfk.name"),"priorityfk")
						.add(Projections.property("title"),"title")
						.add(projectionList()) )
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
	}
}
