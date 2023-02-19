package com.telstabelong.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.telstrabelong.model.Customer;
import com.telstrabelong.model.Number;
import com.telstrabelong.request.dto.CustomerNumberRequest;
import com.telstrabelong.response.dto.CustomerNumberResponseDto;
import com.telstrabelong.response.dto.NumberResponseDto;
import com.telstrabelong.service.PhoneNumberServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class PhoneNumberServiceTest {

	private PhoneNumberServiceImpl phoneNumberServiceImplTest = new PhoneNumberServiceImpl();

	private static Map<String, Customer> customerNumberMap = null;

	@Before
	public void setUp() throws Exception {
		customerNumberMap = new HashMap<>();
		Customer customer = new Customer();
		customer.setFirstName("Kunalan");
		customer.setLastName("Siva");
		customer.setIdNumber("012322PD");
		customer.setIdName("Photo ID");
		List<Number> numbers = new ArrayList<>();
		Number number = new Number();
		number.setNumber(420032321);
		number.setStatus("DEACTIVATED");
		numbers.add(number);
		customer.setNumbers(numbers);
		customerNumberMap.put("012322PD", customer);
	}

	@InjectMocks
	private PhoneNumberServiceImpl phoneNumberService;

	@Test
	public void testFindById() {
		String custId = "012322PD";
		phoneNumberService.findById(custId);
	}

	@Test
	public void testGetAllNumbers() throws NoSuchFieldException, SecurityException {
		List<NumberResponseDto> numbers = new ArrayList<>();
		NumberResponseDto numberResponse1 = new NumberResponseDto();
		numberResponse1.setNumber(420032321);
		numberResponse1.setStatus("DEACTIVATED");
		numbers.add(numberResponse1);

		phoneNumberService.getAllNumbers();

		FieldSetter.setField(phoneNumberServiceImplTest,
				PhoneNumberServiceImpl.class.getDeclaredField("CustomerNumberRepsitory"), customerNumberMap);
		List<NumberResponseDto> actualNumbers = phoneNumberServiceImplTest.getAllNumbers();
		assertEquals(actualNumbers.get(0).getNumber(), numbers.get(0).getNumber());

	}

	@Test
	public void testGetAllNumbersOfCustomer() {
		CustomerNumberRequest customerNumberRequest = new CustomerNumberRequest();
		customerNumberRequest.setCustIdNumber("012322PD");

		CustomerNumberResponseDto csResponse = new CustomerNumberResponseDto();
		csResponse.setFirstName("Kunalan");
		csResponse.setLastName("Siva");
		csResponse.setIdNumber("012322PD");
		csResponse.setIdName("Photo ID");
		List<NumberResponseDto> numListResponse = new ArrayList<>();
		NumberResponseDto numResponse = new NumberResponseDto();
		numResponse.setNumber(420032321);
		numResponse.setStatus("ACTIVATED");
		numListResponse.add(numResponse);
		csResponse.setNumbers(numListResponse);

		phoneNumberService.getAllNumbersOfCustomer(customerNumberRequest);
	}

	@Test
	public void testActivatePhoneNumber() throws NoSuchFieldException, SecurityException {
		CustomerNumberRequest customerNumberRequest = new CustomerNumberRequest();
		customerNumberRequest.setCustIdNumber("012322PD");
		customerNumberRequest.setNumber(420032321);
		
		CustomerNumberRequest customerNumberRequest2 = new CustomerNumberRequest();
		customerNumberRequest2.setCustIdNumber("012322PD");
		customerNumberRequest2.setNumber(420032324);
		
		CustomerNumberRequest customerNumberRequest3 = new CustomerNumberRequest();
		customerNumberRequest3.setCustIdNumber("012322PX");
		customerNumberRequest3.setNumber(420032324);

		phoneNumberService.activatePhoneNumber(customerNumberRequest);

		FieldSetter.setField(phoneNumberServiceImplTest,
				PhoneNumberServiceImpl.class.getDeclaredField("CustomerNumberRepsitory"), customerNumberMap);
		String message = phoneNumberServiceImplTest.activatePhoneNumber(customerNumberRequest);
		assertEquals(message, "The phone number 420032321 is activated");
		
		String message2 = phoneNumberServiceImplTest.activatePhoneNumber(customerNumberRequest2);
		assertEquals(message2, "The phone number 420032324 is not registered yet");
		
		String message3 = phoneNumberServiceImplTest.activatePhoneNumber(customerNumberRequest3);
		assertEquals(message3, "The customer ID 012322PX is not found");

	}

}
