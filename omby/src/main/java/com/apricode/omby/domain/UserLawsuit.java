package com.apricode.omby.domain;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
@AssociationOverrides({
		@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "userId")),
		@AssociationOverride(name = "pk.lawsuit", joinColumns = @JoinColumn(name = "lawsuitId")) })
public class UserLawsuit implements java.io.Serializable {
	    private static final long serialVersionUID = 1L;
		private UserLawsuitId pk = new UserLawsuitId();
		
		private Integer status;
		
		
		@EmbeddedId
		public UserLawsuitId getPk() {
			return pk;
		}
		public void setPk(UserLawsuitId pk) {
			this.pk = pk;
		}
		@Transient
		public User getUser() {
			return getPk().getUser();
		}
		public void setUser(User user) {
			getPk().setUser(user);
		}
		@Transient
		public Lawsuit getLawsuit() {
			return getPk().getLawsuit();
		}
		public void setLawsuit(Lawsuit lawsuit) {
			getPk().setLawsuit(lawsuit);
		}
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			UserLawsuit that = (UserLawsuit) o;

			if (getPk() != null ? !getPk().equals(that.getPk())
					: that.getPk() != null)
				return false;

			return true;
		}
		public int hashCode() {
			return (getPk() != null ? getPk().hashCode() : 0);
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Integer getStatus() {
			return status;
		}
}