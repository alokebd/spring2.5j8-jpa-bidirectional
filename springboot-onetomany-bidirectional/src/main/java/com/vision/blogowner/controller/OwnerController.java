package com.vision.blogowner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vision.blogowner.controller.dto.BlogDto;
import com.vision.blogowner.controller.dto.ResponseBlog;
import com.vision.blogowner.controller.dto.ResponseOwner;
import com.vision.blogowner.entity.Owner;
import com.vision.blogowner.service.OwnerService;

@RestController
@RequestMapping("/api")
public class OwnerController {
		
	@Autowired
	private OwnerService ownerService;

	
	@PostMapping("/owners")
	public ResponseEntity<String> saveOwner(@RequestBody Owner owner) {
		Owner ownerIn = new Owner(owner.getName(), owner.getEmail());
		return ownerService.saveOwner(ownerIn, owner.getBlogList());
	}
	
	
	@PostMapping("/blogs/{id}")
	public ResponseEntity<String> addBlogByOwnerId(@PathVariable(name = "id") String id, @RequestBody Owner owner) {
		return ownerService.addBlogByOwnerId(id, owner.getBlogList());
	}
    
	@GetMapping("/owners/{id}")
	public  ResponseEntity<ResponseOwner>  getOwner(@PathVariable(name = "id") String id) {
		return ownerService.getOwner(id);
	}

	@GetMapping("/blogs/{id}")
	public ResponseEntity<ResponseBlog> getBlog(@PathVariable(name = "id") String id) {
		return ownerService.getBlog(id);
	}
}
