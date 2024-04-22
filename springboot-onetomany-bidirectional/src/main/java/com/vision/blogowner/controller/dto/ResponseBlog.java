package com.vision.blogowner.controller.dto;

import lombok.Data;

@Data
public class ResponseBlog{
	private OwnerDto owner;
    private BlogDto blog;
}
