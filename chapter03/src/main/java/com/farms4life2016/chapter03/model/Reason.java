package com.farms4life2016.chapter03.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A class that represents a reason as to why a pokemon 
 * is widely considered a waifu among the pokemon community
 */
public class Reason implements Comparable<Reason> {
    private String explaination;  // text explaining why a pokemon is considered a waifu
    private int votes;  // allows users to vote on which reasons hold the most water

    public Reason() {
    }

    public Reason(String explaination) {
        setExplaination(explaination);
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    /*
     * not sure why the book authors need to override all these 
     * functions /shrug
     */

    @Override
    public boolean equals(Object o) {
        // return true iff explainations are the same
        if (this == o) return true;
        if (!(o instanceof Reason)) return false;
        Reason r = (Reason) o;
        return Objects.equals(this.getExplaination(), r.getExplaination());
    }

    @Override
    public int hashCode() {
        // returns the hash code of the explaination string
        return Objects.hash(getExplaination());
    }

    @Override
    public String toString() {
        // fancy way to format a string
        StringJoiner sj = new StringJoiner(", ", Reason.class.getSimpleName()+ "[", "]");
        sj.add("votes=\'" + getVotes() + "\'");
        sj.add("explaination=\'" + getExplaination() + "\'");
        return sj.toString();
    }

    
    @Override
    public int compareTo(Reason other) {
        /*
        * compares votes first and then compares explainations.
        * higher votes are prioritized over lower votes,
        * explaination comparison is lexicographical
        */
        int comp = Integer.compare(other.getVotes(), getVotes());
        if (comp == 0) {
            comp = getExplaination().compareTo(other.getExplaination());
        }
        return comp;
    }
    
}

