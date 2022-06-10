package com.oracle.tech.interview.assignment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OracleTechAssignmentTest {

	@Test(expected = IllegalArgumentException.class)
	public void validateInputDataTest() {
		List<String> list = new ArrayList<>();
		list.add("23432254,2345,us_east,RedTeam,ProjectApple,3445s");
		OracleTechAssignment.validateInputData(list);
	}
}
