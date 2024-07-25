package com.farms4life2016.chapter07;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * a simple Java class to represent our candidates in the database
 */
public class Candidate implements Comparable<Candidate> {

    private int c_id;
    private String c_name;

    public Candidate() {}

    public Candidate(final int c_id, final String c_name) {
        this.c_id = c_id;
        this.c_name = c_name;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Candidate.class.getSimpleName() + "[", "]")
            .add("c_id = " + c_id)
            .add("c_name = " + c_name).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Candidate)) return false;
        Candidate other = (Candidate) obj;
        return other.getC_id() == this.c_id && 
            Objects.equals(this.c_name.toLowerCase(), other.getC_name().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.c_id, this.c_name);
    }

    @Override
    public int compareTo(Candidate other) {
        return other.getC_name().toLowerCase() .compareTo( c_name.toLowerCase() );
    }
    
}
