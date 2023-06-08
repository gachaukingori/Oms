package com.projects.oms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@WebAppConfiguration
@ContextConfiguration

@SpringBootTest


class OmsApplicationTests {
    
    Calculator calculator = new Calculator();
    @Test
     public void shouldAddNumbers(){
         //given
        int a  = 20;
        int b = 30;
        
        // when 
        int result = calculator.add(a, b);
        
       // then
        int expected = 50;
        assertThat(result).isEqualTo(expected);
     }


}

class Calculator{
    int add(int a, int b){
        return a + b;
    }
}

