package com.farms4life2016.chapter07;

import java.util.Objects;

/**
 * this is a literal "reskin" or chapter04's abstract Furry class lmao
 * 
 * on second thought "reskin" might not be a good word to use here...
 */
public class Greeting { 

    String message; 

    public Greeting() {

    }

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String animal) {
        this.message = animal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Greeting)) return false;
        Greeting greet = (Greeting) obj; 
        return Objects.equals(greet.getMessage(), this.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

}
