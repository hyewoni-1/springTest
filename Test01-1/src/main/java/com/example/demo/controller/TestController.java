package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("test")

public class TestController {
	@GetMapping("testGetMapping")
	public String testController() {
		return "Hello World!";
		
	}
	
	
	@GetMapping("/{id}")
	public String testControllerWithPathVariable(
			@PathVariable(value="id",required=false) int id  ) {
		return "Hello World!"+id;
	
	}
	
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(
			@RequestParam(value="id",required=false) int id) {
		return "Hello World" + id;
	}

	

	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(
			@RequestBody TestRequestBodyDTO testRequestBodyDTO ) {
		return "Hello World ID " + testRequestBodyDTO.getId()+ 
				" message : " + testRequestBodyDTO.getMessage();
		}

	@GetMapping("/testResponseBody")
	public  ResponseDTO<String> testControllerResponseBody(){
		List<String> list =new ArrayList<>();
		list.add("Hello World i'm ResponseDTO");
		
		ResponseDTO<String> response  = ResponseDTO.<String>builder().data(list).build();
		
		return response;
	}
	

		
	}
	
	














