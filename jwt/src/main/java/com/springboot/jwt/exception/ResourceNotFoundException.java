package com.springboot.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String resoureName;
	private String fieldName;
	private Object fieldObject;
	
	public ResourceNotFoundException(String resoureName, String fieldName, Object fieldObject) {
		super(String.format("%s not found with %s : %s", resoureName,fieldName,fieldObject));
		this.resoureName = resoureName;
		this.fieldName = fieldName;
		this.fieldObject = fieldObject;
	}

	public String getResoureName() {
		return resoureName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldObject() {
		return fieldObject;
	}

}
