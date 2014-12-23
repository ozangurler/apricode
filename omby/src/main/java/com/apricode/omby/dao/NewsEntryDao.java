package com.apricode.omby.dao;

import com.apricode.omby.dao.Dao;
import com.apricode.omby.domain.NewsEntry;


/**
 * Definition of a Data Access Object that can perform CRUD Operations for {@link NewsEntry}s.
 * 
 * @author Philip W. Sorst <philip@sorst.net>
 */
public interface NewsEntryDao extends Dao<NewsEntry, Long>
{

}