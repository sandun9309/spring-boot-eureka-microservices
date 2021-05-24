package com.sandun.courseservice.controller;

import com.sandun.courseservice.dto.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("courses")
@RestController
public class CourseController
{
	List<Course> courseList = Arrays.asList(
			new Course( 1, "CC1", "Liberal Arts","6 months", 4 ),
			new Course( 2, "CC2", "Economics","18 months", 12 ),
			new Course( 3, "CC3", "Humanities and Social Sciences","12 months", 8 ),
			new Course( 4, "CC4", "Science and Engineering","6 months", 4 )
	);

	@GetMapping("/{id}")
	public Course findCourseById( @PathVariable long id )
	{
		return courseList.stream().filter( course -> course.getId() == id ).findFirst().orElse( new Course() );
	}
}
