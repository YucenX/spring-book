package com.farms4life2016.chapter03.mem03;

import org.springframework.stereotype.Component;

import com.farms4life2016.chapter03.Normalizer;

@Component("cappy")
/**
 * capitalizes every white-space-separated word
 */
public class CappyNormalizer implements Normalizer {

    @Override
    public String transform(String input) {
        // transforms the string such that the first letter of 
        // each word will be capitalized if possible and the rest will be lowercase
        // also trims your string
        input.trim();
        String[] words = input.split(" "); // get a list of words (space-separated groups of chars)
        StringBuilder result = new StringBuilder();

        // capitalize each word and add to string
        for (String s : words) {
            if (s.length() > 0) {
                result.append(Character.toUpperCase(s.charAt(0)));
                if (s.length() > 1) result.append(s.substring(1).toLowerCase());
                result.append(' ');
            }
        }
        // remove excess whitespace
        return result.toString().trim();
    }
    
}
