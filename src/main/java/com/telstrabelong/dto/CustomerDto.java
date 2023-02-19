package com.telstrabelong.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {

	private String idNumber;

	private String idName;

	private String firstName;

	private String lastName;

	private List<NumberDto> numbers;

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the idName
	 */
	public String getIdName() {
		return idName;
	}

	/**
	 * @param idName the idName to set
	 */
	public void setIdName(String idName) {
		this.idName = idName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the numbers
	 */
	public List<NumberDto> getNumbers() {
		return numbers;
	}

	/**
	 * @param numbers the numbers to set
	 */
	public void setNumbers(List<NumberDto> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "CustomerDto {" + "idNumber='" + idNumber + '\'' + ", idName='" + idName + '\'' + ", firstName='"
				+ firstName + '\'' + ", lastName='" + lastName + '\'' + ", numbers=" + numbers + '}';
	}

}
