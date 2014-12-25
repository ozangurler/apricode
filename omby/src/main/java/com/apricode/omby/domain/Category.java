package com.apricode.omby.domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
@Entity
@Table(name="APRI_ECOMMERCE_CATEGORY")
public class Category {
	private static final Log logger = LogFactory.getLog(Category.class);
	// According To JPA annotations should be either on attribute or setter/getters
	// if both ignores some and give run time error
	// Could not determine type for: java.util.Set, at table: APRI_ECOMMERCE_CATEGORY
	private static final long serialVersionUID = -304318171766862684L;
	private Long id;

	private Set<LocalString> localName = new HashSet<LocalString>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval=true, 
			cascade = {CascadeType.ALL})
	public Set<LocalString> getLocalName() {
		return localName;
	}
	public void setLocalName(Set<LocalString> localName) {
		this.localName = localName;
	}
	@Transient
	public List<LocalString> getLocalNameAsList() {
		List<LocalString> list = new ArrayList<LocalString>(localName);
		return list;
	}	
	
	
	@Transient
	public  String getCategoryName(String locale) {
	    for(LocalString l :  this.localName)
	    {
	    	if (l.getLocale().equalsIgnoreCase(locale))
	    		return l.getText();
	    }
	    return "";
	}

	@Transient
	public  void setCategoryName(String locale, String text) {
	    for(LocalString l :  this.localName)
	    {
	    	if (l.getLocale().equalsIgnoreCase(locale))
	    	{
	    		l.setText(text);
	    		return;
	    	}
	    }
	    LocalString loc = new LocalString();
	    loc.setLocale(locale);
	    loc.setCategory(this);
	    loc.setText(text);
	    this.localName.add(loc);
	    
	    
	}	
	
	
	
	
	
	
	
	
	
	
	
	private String name;		
	private Set <CategoryProduct> categoryProducts= new HashSet<CategoryProduct>(0);
	
	
	
	
	
	
	

	public void removeCategoryProducts() {
		Iterator<CategoryProduct> i = categoryProducts.iterator();
		while (i.hasNext()){
			CategoryProduct objRem = i.next();
			objRem.setCategory(null);
			objRem.setProduct(null);
			objRem = null;
		}
	}
	@Transient
	public List <CategoryProduct> getCategoryProductsShort(){
		List<CategoryProduct> shortList  = new ArrayList<CategoryProduct>();
		
		Iterator<CategoryProduct> i = categoryProducts.iterator();
		int counter = 0;
		int loopSize = categoryProducts.size()>=2 ? 2 : categoryProducts.size();
		while (counter < loopSize ){
			CategoryProduct catpro = i.next();
			counter++;
			shortList.add(catpro);
			logger.debug("ISTE MENU -->" + catpro.getPk().getProduct().getName());
		}
		return shortList;
	}	

	
	
	
	public void setCategoryProducts(Set <CategoryProduct> categoryProducts){this.categoryProducts = categoryProducts;}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.category", orphanRemoval=true, 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH} )
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public Set <CategoryProduct> getCategoryProducts(){return categoryProducts;}	

	@SuppressWarnings("unchecked")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() { return this.id;}
	public void setId(Long id) {this.id = id;}
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
}