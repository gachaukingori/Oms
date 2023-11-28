package com.projects.oms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ContextConfiguration

@SpringBootTest
class OmsApplicationTests {


    @ParameterizedTest
    @ValueSource(strings = {"race car", "madam", "mad dam", "able was I ere I saw elba"})
    void checksIfStringIsaPalindrome(String word) {
        boolean expected = StringUtils.isPalindrome(word);
        assertThat(expected).isEqualTo(true);
    }

}


class StringUtils {
    static boolean isPalindrome(String word) {
        String formattedWord = word.toLowerCase().replaceAll(" ", "");
        StringBuilder palindrome = new StringBuilder();
        for (int i = (formattedWord.length() - 1); i >= 0; i--) {
            palindrome.append(formattedWord.charAt(i));
        }
        return formattedWord.contentEquals(palindrome);
    }
}

