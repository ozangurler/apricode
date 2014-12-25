package com.apricode.omby.domain;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "APRI_ECOMMERCE_CATEGORY_PRODUCT")
@AssociationOverrides({
		@AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "productId")),
		@AssociationOverride(name = "pk.category", joinColumns = @JoinColumn(name = "categoryId")) })
public class CategoryProduct implements java.io.Serializable {
	    private static final long serialVersionUID = 1L;
		private CategoryProductId pk = new CategoryProductId();
		
		private Integer status;
		
		
		@EmbeddedId
		public CategoryProductId getPk() {
			return pk;
		}
		public void setPk(CategoryProductId pk) {
			this.pk = pk;
		}
		@Transient
		public Product getProduct() {
			return getPk().getProduct();
		}
		public void setProduct(Product product) {
			getPk().setProduct(product);
		}
		@Transient
		public Category getCategory() {
			return getPk().getCategory();
		}
		public void setCategory(Category category) {
			getPk().setCategory(category);
		}
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			CategoryProduct that = (CategoryProduct) o;

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