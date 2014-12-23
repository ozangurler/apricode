package com.apricode.omby.domain;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;


public class UserAction {

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
    @ManyToOne
    private OptVal optVal;

    /**
     */
    @Size(min = 1, max = 255)
    private String val;

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

	public OptVal getOptVal() {
		return optVal;
	}

	public void setOptVal(OptVal optVal) {
		this.optVal = optVal;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
}
