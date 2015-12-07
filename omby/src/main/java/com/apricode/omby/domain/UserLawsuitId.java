package com.apricode.omby.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Embeddable
@JsonIgnoreProperties
public class UserLawsuitId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Lawsuit lawsuit;
	private User user;
	

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLawsuitId that = (UserLawsuitId) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (lawsuit != null ? !lawsuit.equals(that.lawsuit) : that.lawsuit != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (user != null ? user.hashCode() : 0);
        result = 31 * result + (lawsuit != null ? lawsuit.hashCode() : 0);
        return result;
    }
	@ManyToOne(fetch = FetchType.LAZY) 
//	@JsonBackReference
	public Lawsuit getLawsuit() {
		return lawsuit;
	}

	public void setLawsuit(Lawsuit lawsuit) {
		this.lawsuit = lawsuit;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}