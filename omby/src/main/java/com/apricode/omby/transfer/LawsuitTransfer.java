package com.apricode.omby.transfer;

import com.apricode.omby.domain.Lawsuit;

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

    public static LawsuitTransfer  map (Lawsuit lawsuit){
    	LawsuitTransfer lawsuitTransfer = new LawsuitTransfer();
    	
    	lawsuitTransfer.setId(lawsuit.getId());
    	lawsuitTransfer.setName(lawsuit.getName());
    	lawsuitTransfer.setPublicLawsuit(lawsuit.getPublicLawsuit());
    	lawsuitTransfer.setVersion(lawsuit.getVersion());    	
    	
    	return lawsuitTransfer;
    }
    
    
    public static Lawsuit   map (LawsuitTransfer lawsuitTransfer ){
    	Lawsuit lawsuit = new Lawsuit();
    	
    	lawsuit.setId(lawsuitTransfer.getId());
    	lawsuit.setName(lawsuitTransfer.getName());
    	lawsuit.setPublicLawsuit(lawsuitTransfer.getPublicLawsuit());
    	lawsuit.setVersion(lawsuitTransfer.getVersion());    	
    	
    	return lawsuit;
    }


}
