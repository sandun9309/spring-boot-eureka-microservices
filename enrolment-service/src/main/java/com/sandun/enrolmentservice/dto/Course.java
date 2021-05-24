package com.sandun.enrolmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course
{
	private long id;
	private String courseCode;
	private String courseName;
	private String duration;
	private long noOfSubjects;
}
