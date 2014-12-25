package com.apricode.omby.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class CategoryProductId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Category category;
	private Product product;
	

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryProductId that = (CategoryProductId) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (product != null ? product.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
	@ManyToOne(fetch = FetchType.LAZY) 
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


}