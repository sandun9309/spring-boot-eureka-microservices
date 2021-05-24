package com.sandun.enrolmentservice.controller;

import com.sandun.enrolmentservice.dto.Course;
import com.sandun.enrolmentservice.dto.Enrolment;
import com.sandun.enrolmentservice.dto.EnrolmentResponse;
import com.sandun.enrolmentservice.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("enrolments")
@RestController
public class EnrolmentController
{

	@Autowired
	private WebClient.Builder builder;

	List<Enrolment> enrolmentList = Arrays.asList(
			new Enrolment( 1, 1, 1),
			new Enrolment( 2, 1, 3),
			new Enrolment( 3, 2, 1),
			new Enrolment( 4, 3, 1) ,
			new Enrolment( 4, 3, 2) ,
			new Enrolment( 4, 3, 4)
	);

	@GetMapping("/{id}")
	public EnrolmentResponse findEnrolmentById( @PathVariable long id )
	{
		Enrolment enrolment = enrolmentList.stream().filter( e -> e.getId() == id ).findFirst().orElse( new Enrolment() );

		Student student = builder.build().get()
				.uri( "http://student-service/students/" + enrolment.getStudentId() )
				.retrieve()
				.bodyToMono( Student.class )
				.block();

		Course course = builder.build().get()
				.uri( "http://course-service/courses/" + enrolment.getCourseId() )
				.retrieve()
				.bodyToMono( Course.class )
				.block();
		return new EnrolmentResponse( enrolment.getId(), student, course );
	}

	@GetMapping("/student/{studentId}")
	public List<EnrolmentResponse> findEnrolmentByStudentId( @PathVariable long studentId )
	{
		List<EnrolmentResponse> enrolmentResponseList = new ArrayList<>();

		Student student = builder.build()
				.get()
				.uri( "http://student-service/students/" + studentId )
				.retrieve()
				.bodyToMono( Student.class )
				.block();

		List<Enrolment> filteredEnrolmentList = enrolmentList.stream().filter( enrolment -> enrolment.getStudentId() == studentId ).collect( Collectors.toList() );

		filteredEnrolmentList.forEach( enrolment -> {
			Course course = builder.build()
					.get()
					.uri( "http://course-service/courses/" + enrolment.getCourseId() )
					.retrieve()
					.bodyToMono( Course.class )
					.block();
			enrolmentResponseList.add( new EnrolmentResponse( enrolment.getId(), student, course ) );
		} );
		return enrolmentResponseList;
	}
}
