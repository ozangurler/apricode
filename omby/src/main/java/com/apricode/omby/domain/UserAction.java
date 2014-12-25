package com.apricode.omby.domain;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Size;


@javax.persistence.Entity
public class UserAction {
	
	
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;
    	
	
	
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
