package com.rishabh.pmt.services;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ErrorValidationService {
	
	public ResponseEntity<?> errorValidation(BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			
			HashMap<String, String> errorMap = new HashMap<>();
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<HashMap<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
