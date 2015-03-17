package com.apricode.omby.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
@Entity
@Table
public class Lawsuit implements com.apricode.omby.domain.Entity{
	private static final Log logger = LogFactory.getLog(Lawsuit.class);
	// According To JPA annotations should be either on attribute or setter/getters
	// if both ignores some and give run time error
	// Could not determine type for: java.util.Set, at table: APRI_ECOMMERCE_CATEGORY
	private Set <CategoryProduct> categoryProducts= new HashSet<CategoryProduct>(0);
	private Long id;
	private Integer version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}	
	
	
	public void setCategoryProducts(Set <CategoryProduct> categoryProducts){this.categoryProducts = categoryProducts;}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.category", orphanRemoval=true, 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH} )
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public Set <CategoryProduct> getCategoryProducts(){return categoryProducts;}	
	
	
	
}