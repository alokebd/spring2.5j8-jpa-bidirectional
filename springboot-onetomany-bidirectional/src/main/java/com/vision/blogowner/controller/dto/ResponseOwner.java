package com.vision.blogowner.controller.dto;

import java.util.List;


import lombok.Data;

@Data
public class ResponseOwner {
	private String name;
    private String email;
    private List<BlogDto> blogList;
}
