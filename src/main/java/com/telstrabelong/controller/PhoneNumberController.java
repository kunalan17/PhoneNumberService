package com.telstrabelong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.telstrabelong.request.dto.CustomerNumberRequest;
import com.telstrabelong.response.dto.CustomerNumberResponseDto;
import com.telstrabelong.response.dto.NumberResponseDto;
import com.telstrabelong.response.dto.ResponseDto;
import com.telstrabelong.service.PhoneNumberService;

/**
 * The controller of Customer Phone Number APIs
 * 
 * @author Kunalan
 * @date 19-02-2023
 *
 */
@RestController
@RequestMapping("/phone-number-api")
public class PhoneNumberController {

	@Autowired
	private PhoneNumberService phoneNumberService;

	/**
	 * Get all phone numbers API
	 * 
	 * @return ResponseEntity <List<NumberResponseDto>>
	 */
	@GetMapping(value = "/all-numbers", produces = MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "fallbackGetAllNumbers")
	public ResponseEntity<List<?>> getAllNumbers() {
		List<NumberResponseDto> numbers = phoneNumberService.getAllNumbers();
		return new ResponseEntity<>(numbers, HttpStatus.OK);
	}

	/**
	 * Get all phone numbers of a customer API
	 * 
	 * @param custIdNumber - String
	 * @return ResponseEntity <CustomerNumberResponseDto>
	 */
	@GetMapping(value = "/customer-numbers", produces = MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "fallbackGetAllNumbersOfCustomer")
	public ResponseEntity<?> getAllNumbersOfCustomer(
			@RequestParam(value = "custIdNumber", required = true) String custIdNumber) {
		CustomerNumberRequest customerNumberRequest = new CustomerNumberRequest();
		customerNumberRequest.setCustIdNumber(custIdNumber);
		CustomerNumberResponseDto customerNumbers = phoneNumberService
				.getAllNumbersOfCustomer(customerNumberRequest);
		if (null == customerNumbers.getIdNumber()) {
			ResponseDto responseDto = new ResponseDto();
			responseDto.setField("Invalid custIdNumber");
			responseDto.setMessage(
					"Please enter customer's ID Number that used to obtain the phone numbers (e-g: Driving Licence/ Passport/ Photo Card)");
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(customerNumbers, HttpStatus.OK);
		}
	}

	/**
	 * Activate a number API
	 * 
	 * @param custIdNumber - String
	 * @param number       - Integer
	 * @return ResponseEntity<String>
	 */
	@PostMapping(value = "/activate-number")
	@HystrixCommand(fallbackMethod = "fallbackActivateNumber")
	public ResponseEntity<String> activateNumber(
			@RequestParam(value = "custIdNumber", required = true) String custIdNumber,
			@RequestParam(value = "number", required = true) Integer number) {
		CustomerNumberRequest customerNumberRequest = new CustomerNumberRequest();
		customerNumberRequest.setCustIdNumber(custIdNumber);
		customerNumberRequest.setNumber(number);
		String activationStatus = phoneNumberService.activatePhoneNumber(customerNumberRequest);
		return new ResponseEntity<>(activationStatus, HttpStatus.OK);
	}

	/**
	 * Fallback method of Get all phone numbers API
	 */
	public ResponseEntity<List<?>> fallbackGetAllNumbers(Throwable e) {
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Fallback method of Get all phone numbers of customer API
	 */
	public ResponseEntity<?> fallbackGetAllNumbersOfCustomer(
			@RequestParam(value = "custIdNumber", required = true) String idNumber, Throwable e) {
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Fallback method of Activate a number API
	 */
	public ResponseEntity<String> fallbackActivateNumber(
			@RequestParam(value = "custIdNumber", required = true) String custIdNumber,
			@RequestParam(value = "number", required = true) Integer number, Throwable e) {
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
