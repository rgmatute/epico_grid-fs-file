package com.example.demo.services.dto;

import lombok.Data;

@Data
public class ResourceResponse {

	private byte[] body;

	private String contentType;

	private String contentDisposition;

	private int status;
	
	private String message;

}
