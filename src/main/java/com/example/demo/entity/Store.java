package com.example.demo.entity;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	public String id;

	@Field("filename")
	private String filename;

	@Field("resource_id")
	private String resourceId;

	@Field("size")
	private Long size;

	@Field("content_type")
	private String contentType;

	@Field("is_public")
	private boolean isPublic = true;

}
