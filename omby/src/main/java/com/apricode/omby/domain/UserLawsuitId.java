package com.apricode.omby.domain;
import javax.persistence.Column;



public final class UserLawsuitId {

    /**
     */
    @Column(name = "user_id")
    private String userId;

    /**
     */
    @Column(name = "lawsuit_id")
    private String lawsuitId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLawsuitId() {
		return lawsuitId;
	}

	public void setLawsuitId(String lawsuitId) {
		this.lawsuitId = lawsuitId;
	}
}
