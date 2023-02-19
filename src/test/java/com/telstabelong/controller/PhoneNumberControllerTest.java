package com.telstabelong.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.telstrabelong.controller.PhoneNumberController;
import com.telstrabelong.request.dto.CustomerNumberRequest;
import com.telstrabelong.response.dto.CustomerNumberResponseDto;
import com.telstrabelong.response.dto.NumberResponseDto;
import com.telstrabelong.service.PhoneNumberService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class PhoneNumberControllerTest {

	@InjectMocks
	private PhoneNumberController phoneNumberController;

	@Mock
	private PhoneNumberService phoneNumberService;

	@Test
	public void testGetAllNumbers() {
		Mockito.when(phoneNumberService.getAllNumbers()).thenReturn(new ArrayList<NumberResponseDto>());
		assertNotNull(phoneNumberController.getAllNumbers());
	}

	@Test
	public void testGetAllNumbersOfCustomer() {
		CustomerNumberRequest customerNumberRequest = new CustomerNumberRequest();
		customerNumberRequest.setCustIdNumber("12345678N");
		Mockito.when(phoneNumberService.getAllNumbersOfCustomer(Mockito.any()))
				.thenReturn(new CustomerNumberResponseDto());
		phoneNumberController.getAllNumbersOfCustomer("12345678N");
	}

	@Test
	public void testActivateNumber() {
		Mockito.when(phoneNumberService.activatePhoneNumber(Mockito.any())).thenReturn(new String());
		phoneNumberController.activateNumber("12345678N", 0422443322);
	}
	
	@Test
	public void testFallbackGetAllNumbers() {
		ResponseEntity<?> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		assertEquals(phoneNumberController.fallbackGetAllNumbers(null), responseEntity);
	}

	@Test
	public void testFallbackGetAllNumbersOfCustomer() {
		ResponseEntity<?> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		assertEquals(phoneNumberController.fallbackGetAllNumbersOfCustomer("12345678N", null), responseEntity);
	}
	
	@Test
	public void testFallbackActivateNumber() {
		ResponseEntity<?> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		assertEquals(phoneNumberController.fallbackActivateNumber("12345678N", 0422443322, null), responseEntity);
	}

}
