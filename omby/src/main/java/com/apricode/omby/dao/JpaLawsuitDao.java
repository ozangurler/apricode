package com.apricode.omby.dao;

import org.springframework.stereotype.Repository;

import com.apricode.omby.domain.Lawsuit;
import com.apricode.omby.domain.Role;



@Repository(value="lawsuitDao")
public class JpaLawsuitDao extends JpaDao<Lawsuit, Long> implements LawsuitDao
{
	public JpaLawsuitDao() {
		super(Lawsuit.class);
	}

	public JpaLawsuitDao(Class<Lawsuit> entityClass) {
		super(entityClass);
	}

}
