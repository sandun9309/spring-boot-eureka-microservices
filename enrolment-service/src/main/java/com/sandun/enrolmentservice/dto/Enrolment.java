package com.sandun.enrolmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Enrolment
{
	private long id;
	private long studentId;
	private long courseId;
}
