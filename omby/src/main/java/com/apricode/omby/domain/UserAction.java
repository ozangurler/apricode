package com.apricode.omby.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Size;


@javax.persistence.Entity
public class UserAction implements com.apricode.omby.domain.Entity {
	
	
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;
    	
	

	
    @ManyToOne(	cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    private User user;

    /**
     */
    @ManyToOne(	cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    private Lawsuit lawsuit;

    /**
     */
    @ManyToOne(	cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    private Role role;

    /**
     */
    @ManyToOne(	cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    private ActionType actionType;

    /**
     */
    @ManyToOne(	cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    private OptVal optVal;

    /**
     */
    @Size(min = 1, max = 255)
    private String val;
    
	
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
