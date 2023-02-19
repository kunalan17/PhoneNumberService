package com.telstrabelong.service;

import java.util.List;

import com.telstrabelong.request.dto.CustomerNumberRequest;
import com.telstrabelong.response.dto.CustomerNumberResponseDto;
import com.telstrabelong.response.dto.NumberResponseDto;

/**
 * The service layer of Customer Phone Number APIs
 * 
 * @author Kunalan
 * @date 19-02-2023
 *
 */
public interface PhoneNumberService {
	
	/**
	 * The method getAllNumbers
	 * 
	 * @return List<NumberResponseDto>
	 */
	List<NumberResponseDto> getAllNumbers();
	
	/**
	 * The method getAllNumbersOfCustomer
	 * 
	 * @param customerNumberRequest - CustomerNumberRequest object
	 * @return CustomerNumberResponseDto
	 */
	CustomerNumberResponseDto getAllNumbersOfCustomer(CustomerNumberRequest customerNumberRequest);
	
	/**
	 * The method activatePhoneNumber
	 * 
	 * @param customerNumberRequest - CustomerNumberRequest object
	 * @return String
	 */
	String activatePhoneNumber(CustomerNumberRequest customerNumberRequest);

}
