package com.telstrabelong.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.telstrabelong.dto.CustomerDto;
import com.telstrabelong.dto.NumberDto;
import com.telstrabelong.model.Customer;
import com.telstrabelong.model.Number;
import com.telstrabelong.request.dto.CustomerNumberRequest;
import com.telstrabelong.response.dto.CustomerNumberResponseDto;
import com.telstrabelong.response.dto.NumberResponseDto;

/**
 * The service implementation of Customer Phone Number APIs
 * 
 * @author Kunalan
 * @date 19-02-2023
 *
 */
@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {
	
	private static Logger logger = LoggerFactory.getLogger("PhoneNumberServiceImpl");

	/*** Start Implementation - Static data repository creation ***/
	private static Map<String, Customer> CustomerNumberRepsitory = null;
	static {
		try {
			//Initialise static data from JSON file
			Resource resource = new ClassPathResource("static-data/customer-numbers.json");
			InputStream inputStream = resource.getInputStream();
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String data = bufferReader.lines().collect(Collectors.joining("\n"));
			
			//Covert the JSON data to DTO
			CustomerDto[] customers = new Gson().fromJson(data, CustomerDto[].class);

			//Create CustomerNumberRepository using Map data structure
			Stream<CustomerDto> customerNumberStream = Stream.of(customers);
			CustomerNumberRepsitory = customerNumberStream.map(customerNumber -> {
				return createCustomer(customerNumber);
			}).collect(Collectors.toMap(Customer::getIdNumber, customer -> customer));
			
		} catch (IOException e) {
			logger.error("ERROR:: PhoneNumberServiceImpl static data loading: {}", e);
		}
	}

	/**
	 * Create Customer
	 * 
	 * @param customerDto
	 * @return customer
	 */
	private static Customer createCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setIdNumber(customerDto.getIdNumber());
		customer.setIdName(customerDto.getIdName());
		List<Number> numbers = new ArrayList<>();
		if(null != customerDto.getNumbers() && ! customerDto.getNumbers().isEmpty()) {
			for(NumberDto numberDto : customerDto.getNumbers()) {
				Number number = new Number();
				number.setNumber(numberDto.getNumber());
				number.setStatus(numberDto.getStatus());
				numbers.add(number);
			}
		}
		customer.setNumbers(numbers);
		return customer;
	}

	/**
	 * The Repository findById - will find customer and associated phone numbers by customer ID number  
	 * 
	 * @param idNumber - String
	 * @return Customer
	 */
	public Customer findById(String idNumber) {
		return CustomerNumberRepsitory.get(idNumber);
	}

	/**
	 * The Repository findAll - will returns all the customers and associated phone numbers 
	 * 
	 * @return Collection<Customer>
	 */
	public Collection<Customer> findAll() {
		return CustomerNumberRepsitory.values();
	}

	/**
	 * The Repository update - will update the customer and associated phone numbers 
	 *  
	 * @param customer - Customer object
	 * @return Customer
	 */
	public Customer update(Customer customer) {
		return CustomerNumberRepsitory.replace(customer.getIdName(), customer);
	}
	/*** End Implementation - Static data repository creation ***/

	@Override
	public List<NumberResponseDto> getAllNumbers() {
		List<Customer> customer = new ArrayList<>(findAll());
		List<NumberResponseDto> numbers = new ArrayList<>();
		for (Customer custNum : customer) {
			if (null != custNum.getNumbers()) {
				for (Number number : custNum.getNumbers()) {
					NumberResponseDto numberResponse = new NumberResponseDto();
					numberResponse.setNumber(number.getNumber());
					numberResponse.setStatus(number.getStatus());
					numbers.add(numberResponse);
				}
			}
		}
		return numbers;
	}
	
	@Override
	public CustomerNumberResponseDto getAllNumbersOfCustomer(CustomerNumberRequest customerNumberRequest) {
		Customer customer = findById(customerNumberRequest.getCustIdNumber());
		CustomerNumberResponseDto csResponse = new CustomerNumberResponseDto();
		if (null != customer) {
			csResponse.setFirstName(customer.getFirstName());
			csResponse.setLastName(customer.getLastName());
			csResponse.setIdNumber(customer.getIdNumber());
			csResponse.setIdName(customer.getIdName());
			List<NumberResponseDto> numListResponse = new ArrayList<>();
			if(null != customer.getNumbers() && !customer.getNumbers().isEmpty()) {
				for(Number number : customer.getNumbers()) {
					NumberResponseDto numResponse = new NumberResponseDto();
					numResponse.setNumber(number.getNumber());
					numResponse.setStatus(number.getStatus());
					numListResponse.add(numResponse);
				}
			}
			csResponse.setNumbers(numListResponse);
		}
		return csResponse;
	}

	@Override
	public String activatePhoneNumber(CustomerNumberRequest customerNumberRequest) {
		Customer customer = findById(customerNumberRequest.getCustIdNumber());
		if (null != customer) {
			CustomerNumberRepsitory.replace(customer.getIdNumber(), customer);
			if (null != customer.getNumbers()) {
				for (Number number : customer.getNumbers()) {
					if (number.getNumber().equals(customerNumberRequest.getNumber())) {
						number.setStatus("ACTIVATED");
						update(customer);
						return "The phone number " + customerNumberRequest.getNumber() + " is activated";
					}
				}
			}
			return "The phone number " + customerNumberRequest.getNumber() + " is not registered yet";
		} else {
			return "The customer ID " + customerNumberRequest.getCustIdNumber() + " is not found";
		}
	}

}
