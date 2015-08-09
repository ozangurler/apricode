package com.apricode.omby.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@javax.persistence.Entity
public class Role implements com.apricode.omby.domain.Entity {
	private static final long serialVersionUID = 1L;
	
	public static final String SUER = "SUER";
	public static final String DEFENDANT = "DEFENDANT";
	public static final String PROSECUTOR = "PROSECUTOR";
	public static final String ATTORNEY = "ATTORNEY";
	public static final String JUDGE = "JUDGE";
	public static final String JURY = "JURY";
	public static final String FOLLOWER = "FOLLOWER";
	public static final String WITNESS = "WITNESS";


	public Role() {
		super();
	}




	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;
    	
	public Role(String name){
		this.name = name;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
	
    @Size(min = 1, max = 60)
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
