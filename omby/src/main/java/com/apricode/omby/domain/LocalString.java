package com.apricode.omby.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name="APRI_ECOMMERCE_LOCALSTRING")
public class LocalString  {
	private static final Log logger = LogFactory.getLog(LocalString.class);
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private String locale;
	@Lob
	private String text;

	@ManyToOne
	private Category category;
	
	
	public void setId(Long id) {
		this.id = id;
	}
	@SuppressWarnings("unchecked")
	public Long getId() {
		return id;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Category getCategory() {
		return category;
	}



	
	
}
