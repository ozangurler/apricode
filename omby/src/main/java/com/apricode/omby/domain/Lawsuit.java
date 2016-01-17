package com.apricode.omby.domain;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import com.apricode.omby.transfer.LawsuitTransfer;
@Entity
@Table
public class Lawsuit implements com.apricode.omby.domain.Entity{
	public Lawsuit(String name) {
		super();
		this.name = name;
	}


	public Lawsuit() {
		super();
	}


	private static final Log logger = LogFactory.getLog(Lawsuit.class);
	// According To JPA annotations should be either on attribute or setter/getters
	// if both ignores some and give run time error
	// Could not determine type for: java.util.Set, at table: APRI_ECOMMERCE_Lawsuit
	private Set <UserLawsuit> lawsuitUsers= new HashSet<UserLawsuit>(0);
	private Long id;
	private Integer version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}	
	
	
    @Size(min = 1, max = 60)
    private String name;
    
    private Boolean publicLawsuit = new Boolean(false); // default lawsuit is private

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	

	public void addUser(User user, Role role) throws OmbyRuleException{
		UserLawsuit lawsuitUser = new UserLawsuit();
		lawsuitUser.setLawsuit(this);
		lawsuitUser.setUser(user);
		lawsuitUser.setStatus(new Integer(0));
		lawsuitUser.setRole(role);
		
		
		int countOfJudges = 0 ;
		Set<UserLawsuit> readLawsuitsFromDB = getLawsuitUsers();
		Iterator<UserLawsuit> i = readLawsuitsFromDB.iterator();
		while (i.hasNext()) {
			UserLawsuit readLawsuitFromDB = i.next();			
			
			if ( readLawsuitFromDB.getRole().getName().equals(Role.JUDGE)	)
				{
					countOfJudges++;					
				}
			
			
		}
		
		if (countOfJudges > 0 && role.getName().equals(Role.JUDGE))
		{
			throw new OmbyRuleException();
		}
			
		
		
		lawsuitUsers.add(lawsuitUser);
	}
	
	public void setLawsuitUsers(Set <UserLawsuit> lawsuitUsers){this.lawsuitUsers =lawsuitUsers;}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.lawsuit", orphanRemoval=true, 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH} )
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//	@JsonBackReference
	public Set <UserLawsuit> getLawsuitUsers(){return lawsuitUsers;}


	public Boolean getPublicLawsuit() {
		return publicLawsuit;
	}


	public void setPublicLawsuit(Boolean publicLawsuit) {
		this.publicLawsuit = publicLawsuit;
	}	
	
	@Transient
	public LawsuitTransfer getLawSuitTransfer() {

		LawsuitTransfer lst = new LawsuitTransfer();

		lst.setId(this.getId());
		lst.setName(this.getName());
		lst.setPublicLawsuit(this.getPublicLawsuit());
		lst.setVersion(this.getVersion());

		return lst;
	}

	public void setLawSuitTransfer(LawsuitTransfer lst) {

		if (lst.getId().equals(this.getId())) {

			this.setName(lst.getName());
			this.setPublicLawsuit(lst.getPublicLawsuit());
			this.setVersion(lst.getVersion());
		}

	}
	
}