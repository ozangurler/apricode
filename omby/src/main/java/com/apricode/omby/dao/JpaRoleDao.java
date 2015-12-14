package com.apricode.omby.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apricode.omby.domain.Role;
import com.apricode.omby.domain.User;

@Repository(value="roleDao")
public class JpaRoleDao extends JpaDao<Role, Long> implements RoleDao
{

	
	public JpaRoleDao() {
		super(Role.class);
	}

	public JpaRoleDao(Class<Role> entityClass) {
		super(entityClass);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Role findByName(String name)
	{
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Role> criteriaQuery = builder.createQuery(this.entityClass);

		Root<Role> root = criteriaQuery.from(this.entityClass);
		Path<String> namePath = root.get("name");
		criteriaQuery.where(builder.equal(namePath, name));

		TypedQuery<Role> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		List<Role> roles = typedQuery.getResultList();

		if (roles.isEmpty()) {
			return null;
		}

		return roles.iterator().next();
	}

}
