package com.farms4life2016.chapter03;

public interface Normalizer {
    /**
     * takes a string as input and returns a transformed version of it.
     * @param input inputted string, to be transformed
     * @return transformed string, which by default just removes whitespace like String.trim() does.
     */
    default String transform(String input) {
        return input.trim();
    }
}
