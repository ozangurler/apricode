package com.apricode.omby.dao;

import org.springframework.stereotype.Repository;

import com.apricode.omby.domain.Lawsuit;



@Repository(value="lawsuitDao")
public class JpaLawsuitDao extends JpaDao<Lawsuit, Long> implements LawsuitDao
{

}
