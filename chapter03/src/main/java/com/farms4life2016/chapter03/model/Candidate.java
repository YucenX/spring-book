package com.farms4life2016.chapter03.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a potiential candidate that people might consider a waifumon
 */
public class Candidate {

    // name of the candidate's underlying pokemon species
    private String speciesName;
    // map of reasons to their corresponding objects (which additionally stores votes)
    private Map<String, Reason> reasons = new HashMap<>();

    /*
     * will probably add a pointer to the underlying Pokemon
     * or convert this class into a Pokemon-representing class
     * ... I just want to keep things simple for now, though it is tempting
     * add other field such as furry=true/false lol
     */

    public Candidate() {}

    public Candidate(String speciesName) {
        setSpeciesName(speciesName);
    }


    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public Map<String, Reason> getReasons() {
        return reasons;
    }

    public void setReasons(Map<String, Reason> reasons) {
        this.reasons = reasons;
    }

    @Override
    public boolean equals(Object o) {
        // return true iff species name are the same
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate r = (Candidate) o;
        return Objects.equals(this.getSpeciesName(), r.getSpeciesName());
    }
    
    @Override
    public int hashCode() {
        // returns the hash code of the species name string
        return Objects.hash(getSpeciesName());
    }

    @Override
    public String toString() {
        // fancy way to format a string
        StringJoiner sj = new StringJoiner(", ", Candidate.class.getSimpleName()+ "[", "]");
        sj.add("speciesName=\'" + getSpeciesName() + "\'");
        sj.add("reasons=\'" + getReasons() + "\'");
        return sj.toString();
    }

    // i copied the overrides from reason so there might be some copy-paste errors
    
}
