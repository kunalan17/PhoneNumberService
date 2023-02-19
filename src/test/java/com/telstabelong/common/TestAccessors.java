package com.telstabelong.common;

import org.junit.Test;

import com.telstrabelong.dto.CustomerDto;
import com.telstrabelong.dto.NumberDto;
import com.telstrabelong.model.Customer;
import com.telstrabelong.request.dto.BaseRequest;
import com.telstrabelong.request.dto.CustomerNumberRequest;
import com.telstrabelong.response.dto.CustomerNumberResponseDto;
import com.telstrabelong.response.dto.NumberResponseDto;
import com.telstrabelong.response.dto.ResponseDto;

public class TestAccessors {
	
	@Test
	public void testDto() {
		PojoTestUtils.validateAccessors(CustomerDto.class);
		PojoTestUtils.validateAccessors(NumberDto.class);
		PojoTestUtils.validateAccessors(BaseRequest.class);
		PojoTestUtils.validateAccessors(CustomerNumberRequest.class);
		PojoTestUtils.validateAccessors(CustomerNumberResponseDto.class);
		PojoTestUtils.validateAccessors(NumberResponseDto.class);
		PojoTestUtils.validateAccessors(ResponseDto.class);
	}
	
	@Test
	public void testModel() {
		PojoTestUtils.validateAccessors(Customer.class);
		PojoTestUtils.validateAccessors(com.telstrabelong.model.Number.class);
	}

}
