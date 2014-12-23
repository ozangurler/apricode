package com.apricode.omby.dao;

import java.util.List;

import com.apricode.omby.domain.Entity;


public interface Dao<T extends Entity, I>
{

	List<T> findAll();


	T find(I id);


	T save(T newsEntry);


	void delete(I id);

}