package com.apricode.omby.domain;
import java.math.BigDecimal;

import javax.persistence.ManyToOne;


public class UserLawsuitRoleActionGrade {

    /**
     */
    @ManyToOne
    private User user;

    /**
     */
    @ManyToOne
    private Lawsuit lawsuit;

    /**
     */
    @ManyToOne
    private Role role;

    /**
     */
    @ManyToOne
    private ActionType actionType;

    /**
     */
    private BigDecimal grade;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lawsuit getLawsuit() {
		return lawsuit;
	}

	public void setLawsuit(Lawsuit lawsuit) {
		this.lawsuit = lawsuit;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}
}
