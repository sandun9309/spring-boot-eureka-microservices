package com.sandun.studentservice.controller;

import com.sandun.studentservice.dto.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("students")
@RestController
public class StudentController
{

	List<Student> studentList = Arrays.asList(
			new Student( 1, "Anne", "Henderson" ),
			new Student( 2, "Jason", "Sean" ),
			new Student( 3, "Phil", "Campbell" ),
			new Student( 4, "Emma", "Gray" ) );

	@GetMapping("/{id}")
	public Student findStudentById( @PathVariable long id )
	{
		return studentList.stream().filter( student -> student.getId() == id ).findFirst().orElse( new Student() );
	}

}
