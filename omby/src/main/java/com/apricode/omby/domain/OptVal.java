package com.apricode.omby.domain;
import javax.validation.constraints.Size;


public class OptVal {

    /**
     */
    @Size(min = 1, max = 60)
    private String valCode;

	public String getValCode() {
		return valCode;
	}

	public void setValCode(String valCode) {
		this.valCode = valCode;
	}
}
