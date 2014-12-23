package com.apricode.omby.domain;
import javax.persistence.ManyToOne;


public class UserLawsuit {

    public UserLawsuit() {
    	super();
    	System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzDEFAULT CONSTRUCTORzzzzzzzzzzzzzzzzzzzzzz");
		
	}

	/**
     */
    @ManyToOne
    private Role role;

    /**
     */
    @ManyToOne
    private User user;

    /**
     */
    @ManyToOne
    private Lawsuit lawsuit;
    
public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

//  CODE SHOULD BE ADDED TO JPA CLASS AFTER CODE GENERATION
  public UserLawsuit(User user,Lawsuit lawsuit, Role role) {
	  System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX PARAMETRELI CONSTxxxxx");
      this.setRole (role);
      this.user = user;
      this.lawsuit = lawsuit;
      
      
    //  this.setId(new UserLawsuitId(user.getId().toString(),  lawsuit.getId().toString()) );
    
      
      
      // If User or Lawsuit  Guarantee referential integrity
      user.getLawsuits().add(this);
      lawsuit.getUsers().add(this);
  }

    
}
