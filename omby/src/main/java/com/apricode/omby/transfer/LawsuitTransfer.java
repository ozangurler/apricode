package com.apricode.omby.transfer;

public class LawsuitTransfer {
	private Long id;
	private Integer version;
    private String name;    
    private Boolean publicLawsuit = new Boolean(false);
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getPublicLawsuit() {
		return publicLawsuit;
	}
	public void setPublicLawsuit(Boolean publicLawsuit) {
		this.publicLawsuit = publicLawsuit;
	} 

    

}
