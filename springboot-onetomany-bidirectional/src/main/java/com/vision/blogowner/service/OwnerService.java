package com.vision.blogowner.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.vision.blogowner.controller.dto.BlogDto;
import com.vision.blogowner.controller.dto.OwnerDto;
import com.vision.blogowner.controller.dto.ResponseBlog;
import com.vision.blogowner.controller.dto.ResponseOwner;
import com.vision.blogowner.entity.Blog;
import com.vision.blogowner.entity.Owner;
import com.vision.blogowner.repository.BlogRepository;
import com.vision.blogowner.repository.OwnerRepository;

@Service
public class OwnerService {
	
	private OwnerRepository ownerRepository;
	private BlogRepository blogRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OwnerService(OwnerRepository ownerRepository, BlogRepository blogRepository) {
		this.ownerRepository = ownerRepository;
		this.blogRepository = blogRepository;
	}
	
	public ResponseEntity<String> saveOwner(Owner ownerIn, List<Blog> blogList) {
		List<Blog> blogs = new ArrayList<>();
		try {
			for (Blog blogIn : blogList) {
				Blog blog = new Blog(blogIn.getTitle(), blogIn.getCategory(), blogIn.getContent());
				blog.setOwner(ownerIn);
				blogs.add(blog);
			 }
			 ownerIn.setBlogList(blogs);
			 ownerRepository.save(ownerIn);
			}
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		    }
		return ResponseEntity.ok().body("Owner saved");
	}
	
		
	public ResponseEntity<String> addBlogByOwnerId(String id, List<Blog> blogList) {
		Owner ownerDb = ownerRepository.getById(Integer.valueOf(id));
		if (ownerDb !=null && blogList !=null && blogList.size()>0) {
			List<Blog> blogs = new ArrayList<>();
			for (Blog blogIn : blogList) {
				Blog blog = new Blog(blogIn.getTitle(), blogIn.getCategory(), blogIn.getContent());
				blogs.add(blog);
				blog.setOwner(ownerDb);
			 }
			 ownerDb.setBlogList(blogs);
			 ownerRepository.save(ownerDb);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found owner or blogs");
		}
		return ResponseEntity.ok().body("Blog saved");
	}
	
	public ResponseEntity<ResponseOwner> getOwner (String id) {
		Owner ownerOut = ownerRepository.getById(Integer.valueOf(id));
		
		Owner ownerIn = new Owner(ownerOut.getName(), ownerOut.getEmail());
		List<Blog> blogListIn = ownerOut.getBlogList();
		List<Blog> blogs = new ArrayList<>();
		if (blogListIn !=null && blogListIn.size()>0) {
		for (Blog blogIn : blogListIn) {
			Blog blog = new Blog(blogIn.getTitle(), blogIn.getCategory(), blogIn.getContent());
			blog.setOwner(ownerIn);
			blogs.add(blog);
		 }
		 ownerIn.setBlogList(blogs);
		 //System.out.println("\nOwner details :: \n" + ownerIn);
		 //System.out.println("\nList of Blogs :: \n" + ownerIn.getBlogList());
		 
		 ResponseOwner responseOwner = modelMapper.map(ownerIn, ResponseOwner.class);
		 return ResponseEntity.ok().body(responseOwner);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseOwner());
		}
	}
	
	public ResponseEntity<ResponseBlog> getBlog (String id) {
		// fetch Blog
		Blog blogOut = blogRepository.getById(Integer.valueOf(id));
			if (blogOut != null) {
			//System.out.println("\nBlog details :: \n" + blogOut);
			//System.out.println("\nOwner details :: \n" + blogOut.getOwner());
			Owner ownerOut =blogOut.getOwner();
			ResponseBlog data = new ResponseBlog();
			BlogDto blogDto = new BlogDto();
			blogDto.setTitle(blogOut.getTitle());
			blogDto.setCategory(blogOut.getCategory());
			blogDto.setContent(blogOut.getContent());
			OwnerDto ownerDto = new OwnerDto();
			ownerDto.setId(ownerOut.getId());
			ownerDto.setName(ownerOut.getName());
			ownerDto.setEmail(ownerOut.getEmail());
			data.setOwner(ownerDto);
			data.setBlog(blogDto);
			return ResponseEntity.ok().body(data);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBlog());
		}
	}
}
