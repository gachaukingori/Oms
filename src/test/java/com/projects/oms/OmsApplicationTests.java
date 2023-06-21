package com.projects.oms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


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
        assertThat(result).isEqualTo(50);

     }

     @ParameterizedTest
    @ValueSource(strings = {"race car", "madam", "mad dam", "able was I ere I saw elba"})
    void checksIfStringIsaPalindrome(String word){
       boolean expected = StringUtils.isPalindrome(word);
         assertThat(expected).isEqualTo(true);
     }

}

class Calculator{
    int add(int a, int b){
        return a + b;
    }
}

class StringUtils {
    static boolean isPalindrome(String word){
        String formattedWord = word.toLowerCase().replaceAll(" ","");
        StringBuilder palindrome = new StringBuilder();
        for(int i = (formattedWord.length()-1); i>=0;  i--){
            palindrome.append(formattedWord.charAt(i));
        }
        return formattedWord.contentEquals(palindrome);
    }
}

