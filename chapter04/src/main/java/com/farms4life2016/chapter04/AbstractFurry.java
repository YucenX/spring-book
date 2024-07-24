package com.farms4life2016.chapter04;

import java.util.Objects;

/**
 * A very simple class to demonstrate object lifetimes,
 * although the choice of example is quite questionable.
 * 
 * disclaimer: i do not identify as a furry, 
 * please don't call me that or else i will get visibly upset
 */
abstract class Furry { // why doesn't the book want me to make this class public? idk

    // the underlying animal that the furry is based on, default is "human" lol
    String animal = "human"; 

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Furry)) return false;
        Furry furry = (Furry) obj; // the book says something about accepting subclasses
        return Objects.equals(furry.getAnimal(), this.animal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animal);
    }

}
