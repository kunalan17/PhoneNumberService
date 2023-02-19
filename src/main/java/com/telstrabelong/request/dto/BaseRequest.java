package com.telstrabelong.request.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BaseRequest {
	
	@NotNull
	@NotEmpty
	private String custIdNumber;
	
	private String custIdName;
	
	private String custFirstName;
	
	private String custLastName;

	/**
	 * @return the custIdNumber
	 */
	public String getCustIdNumber() {
		return custIdNumber;
	}

	/**
	 * @param custIdNumber the custIdNumber to set
	 */
	public void setCustIdNumber(String custIdNumber) {
		this.custIdNumber = custIdNumber;
	}

	/**
	 * @return the custIdName
	 */
	public String getCustIdName() {
		return custIdName;
	}

	/**
	 * @param custIdName the custIdName to set
	 */
	public void setCustIdName(String custIdName) {
		this.custIdName = custIdName;
	}

	/**
	 * @return the custFirstName
	 */
	public String getCustFirstName() {
		return custFirstName;
	}

	/**
	 * @param custFirstName the custFirstName to set
	 */
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	/**
	 * @return the custLastName
	 */
	public String getCustLastName() {
		return custLastName;
	}

	/**
	 * @param custLastName the custLastName to set
	 */
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	
}