package com.projects.oms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@SpringBootTest
class OmsApplicationTests {

	Calculator firstTest = new Calculator();
	@Test
	 void shouldAddTwNumbers(){
	// Given
		int num1 = 20;
		int num2 = 30;

		// when
		int result = firstTest.addTwoNumbers(num1, num2);
		//Then
		int expected = 50;
		assertThat(result).isEqualTo(expected);
	}
	class Calculator{

		int addTwoNumbers(int a, int b){
			return  a+b;
		}
	}




}
