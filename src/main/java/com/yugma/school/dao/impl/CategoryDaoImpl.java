package com.yugma.school.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yugma.school.dao.CategoryDao;
import com.yugma.school.model.Category;

@Repository("categoryDaoImpl")
public class CategoryDaoImpl extends AbstractDao<Long, Category> implements CategoryDao {

	public ProjectionList projectionList(){
		return Projections.projectionList()
				.add(Projections.property("id"),"id")
				.add(Projections.property("name"),"name")
				.add(Projections.property("depth"),"depth")
				.add(Projections.property("parentCategoryForCategory.id"),"categoryId");
	}
	
	public void saveCategory(Category category){
		save(category);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> fetchParentCategory(){
		return  createEntityCriteria()
				.add(Restrictions.eq("active", true))
				.add(Restrictions.isNull("parentCategoryForCategory"))
				.setProjection(projectionList())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> fetchCategoryById(long id){
		return (Map<String, Object>) createEntityCriteria()
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("id", id))
				.setProjection(projectionList())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> fetchCategoryByParentCategoryId(long id){
		return createEntityCriteria().add(Restrictions.eq("active", true))
				.add(Restrictions.eq("parentCategoryForCategory.id", id))
				.setProjection(projectionList())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
	}
	
	public Long fetchParentCategoryId(long id){
		return (Long)createEntityCriteria()
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("id", id))
				.setProjection(Projections.property("parentCategoryForCategory.id"))
				.uniqueResult();
	}
	
	public int updateCategory(Map<String,Object> category){
		if(category.isEmpty() && !category.containsKey("id") && category.get("id")==null )
			return 0;
		
		String hql = "update Category set ";
		int count=0;
		
		String name=null;
		
		if(category.containsKey("name") && (String)category.get("name")!=null){
			hql+="name = :name";
			name = category.get("name").toString();
			count++;
		}
		
		if(category.containsKey("categoryId") && category.get("categoryId")!=null){
			if(count>0){
				hql+=",";
			}
			hql+="category_id = " + category.get("categoryId");
		}
		
		if(count==0){
			return 0;
		}
		hql+=" where id = "+category.get("id");
		
		Query query = update(hql);
		if(name!=null){
			query.setString("name", name);
		}
		return query.executeUpdate();
	}
	
	public int incrementDepth(long id){
		return update("update Category set depth = depth+1 where id = "+id)
				.executeUpdate();
	}
	
	public int incrementDepth(Set<Long> ids){
		return update("update Category set depth = depth+1 where id in "+ids)
				.executeUpdate();
	}
	
	public int deleteCategory(long id){
		return update("update Category set active = false where id = "+id)
				.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> fetchStaffComplaintByCategory(){
		return createEntityCriteria()
				.createAlias("staffComplaintForAgainstCategory", "staffComplaint", JoinType.INNER_JOIN)
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("id"), "categoryId")
						.add(Projections.property("name"), "categoryName")
						.add(Projections.count("staffComplaint.id"), "staffComplaintCount"))
				.addOrder(Order.asc("categoryId"))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
}
