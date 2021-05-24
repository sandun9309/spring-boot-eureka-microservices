package com.sandun.enrolmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnrolmentResponse
{
	private long id;
	private Student studentId;
	private Course courseId;
}
