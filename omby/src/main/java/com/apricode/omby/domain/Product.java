package com.apricode.omby.domain;

import java.awt.Color;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.print.attribute.standard.Media;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="APRI_ECOMMERCE_PRODUCT")
public class Product  {
	private static final Log logger = LogFactory.getLog(Product.class);
	private static final int MAX_SIZE_DISPLAY_NAME = 20;
	private static final long serialVersionUID = 4961158999170093699L;
	@Column(unique=true, nullable=false) 
	private String referenceId;
	private String customerReferenceId;
	private Date  changedDate;
	private String name;
	private String name_en;

	private String salesUnit;
	private Integer isInternetSellable;
	private Integer isDeleted;
	private Long id;		

	private Double price= new Double(0);

	private String currency = "TL";
	private Double vatRatio = new Double(0.18);
	private Set <CategoryProduct> categoryProducts = new HashSet<CategoryProduct>(0);

	private Set<LocalLob> contentStrings = new HashSet<LocalLob>();

	


	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product", orphanRemoval=true, 
			cascade = {CascadeType.ALL})
	public Set<LocalLob> getContentStrings() {
		return contentStrings;
	}
	public void setContentStrings(Set<LocalLob> contentStrings) {
		this.contentStrings = contentStrings;
	}
	@Transient
	public List<LocalLob> getContentStringsAsList() {
		List<LocalLob> list = new ArrayList<LocalLob>(contentStrings);
		return list;
	}	
	
	
	@Transient
	public  String getContent(String locale) {
	    for(LocalLob l :  this.contentStrings)
	    {
	    	if (l.getLocale().equalsIgnoreCase(locale))
	    		return l.getText();
	    }
	    return "";
	}

	@Transient
	public  void setContent(String locale, String text) {
	    for(LocalLob l :  this.contentStrings)
	    {
	    	if (l.getLocale().equalsIgnoreCase(locale))
	    	{
	    		l.setText(text);
	    		return;
	    	}
	    }
	    LocalLob loc = new LocalLob();
	    loc.setLocale(locale);
	    loc.setProduct(this);
	    loc.setText(text);
	    this.contentStrings.add(loc);
	    
	    
	}



	
	
	@Transient
	public String getShortName(){
		return name.substring(0, name.length()>MAX_SIZE_DISPLAY_NAME?MAX_SIZE_DISPLAY_NAME:name.length()   ) + "...";
	}
	
	@Transient
	public String getShortName(String lang){
		return getName(lang).substring(0, getName(lang).length()>MAX_SIZE_DISPLAY_NAME?MAX_SIZE_DISPLAY_NAME:getName(lang).length()   ) + "...";
	}	
	
	public void merge(Product inProduct) {
		this.setChangedDate(inProduct.getChangedDate());
		this.setCustomerReferenceId(inProduct.getCustomerReferenceId());
		this.setIsDeleted(inProduct.getIsDeleted());
		this.setIsInternetSellable(inProduct.getIsInternetSellable());
		this.setName(inProduct.getName());
		this.setReferenceId(inProduct.getReferenceId());
		this.setSalesUnit(inProduct.getSalesUnit());
	}	

		


	@SuppressWarnings("unchecked")	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId(){return this.id;}
	
	public void setCategoryProducts(Set <CategoryProduct> categoryProducts){this.categoryProducts = categoryProducts;}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.product", orphanRemoval=true, 
			cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public Set <CategoryProduct> getCategoryProducts(){return categoryProducts;}
	






		
	
	public void setName(String name) {this.name = name;}
	
	public String getName(String language) {
		if (language ==null || language.equalsIgnoreCase("tr")) {
			return name;
		} else {
			if (name_en != null)
				return name_en;
			else
				return name;
		}

	}
	
	public String getName() {return name;}
	public void setName_en(String name_en) {this.name_en = name_en;	}
	public String getName_en() {return name_en;	}
	public String getReferenceId() {return referenceId;}
	public void setReferenceId(String referenceId) {this.referenceId = referenceId;}
	public String getCustomerReferenceId() {return customerReferenceId;}
	public void setCustomerReferenceId(String customerReferenceId){this.customerReferenceId = customerReferenceId;}
	public Date getChangedDate() {return changedDate;}
	public void setChangedDate(Date changedDate) {this.changedDate = changedDate;}
	public String getSalesUnit() {return salesUnit;}
	public void setSalesUnit(String salesUnit) {this.salesUnit = salesUnit;}
	public Integer getIsInternetSellable() {return isInternetSellable;}
	public void setIsInternetSellable(Integer isInternetSellable){this.isInternetSellable = isInternetSellable;}
	public Integer getIsDeleted() {	return isDeleted;	}
	public void setIsDeleted(Integer isDeleted) {this.isDeleted = isDeleted;}
	public void setId(Long id) {this.id = id;}



	public void setPrice(Double price) {this.price = price;	}
	public Double getPrice() {return price;}
	public void setVatRatio(Double vatRatio) {this.vatRatio = vatRatio;	}
	public Double getVatRatio() {return vatRatio;}
	public void setCurrency(String currency) {this.currency = currency;}
	public String getCurrency() {return currency;}	



	









}