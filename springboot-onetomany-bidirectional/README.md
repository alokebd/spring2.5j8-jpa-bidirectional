# Spring Boot project for bidirectional relationship in OneToMany association using Hibernate and JPA
# Test
1). POST: http://localhost:8080/api/owners
{
  "name": "Alex Jain",
  "email": "alex@gmail.com",
  "blogList": [{
    "title": "JPA Bidirectional Coding",
	"catagory": "JAVA",
	"content": "One to Many and Many to One Relationships"
  },
  {
    "title": "Spring Boot 3",
    "catagory": "JAVA",
	"content": "New changes for security"
  }
  ]
}
2). GET: http://localhost:8080/api/blogs?id=1
{
"blogList": [{
    "title": "JPA Bidirectional Coding",
	"catagory": "JAVA",
	"content": "One to Many and Many to One Relationships"
  }
}
3). GET: http://localhost:8080/api/owners/1
4). GET: http://localhost:8080/api/blogs/2
