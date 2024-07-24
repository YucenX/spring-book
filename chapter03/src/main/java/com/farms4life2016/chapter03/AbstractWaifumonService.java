package com.farms4life2016.chapter03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.farms4life2016.chapter03.model.Candidate;
import com.farms4life2016.chapter03.model.Reason;

/**
 * A class that implements some interfaces and forces some methonds? idk
 * it contains some verbose stuff to be used later...
 */
public abstract class AbstractWaifumonService implements WaifumonService, Resettable {
    // maps strings to candidates by name
    private Map<String, Candidate> pokemons = new HashMap<>();

    /**
     * override this function if you need to perform some formatting or 
     * validation checks on the string before using it in the API as a Candidate name
     * @param input
     * @return
     */
    protected String transformCandidate(String input) {
        return input;
    }

    /**
     * override this function if you need to perform some formatting or 
     * validation checks on the string before using it in the API as a Reason name
     * @param input
     * @return
     */
    protected String transformReason(String input) {
        return input;
    }

    @Override
    public void reset() {
        pokemons.clear(); // clean out the map
    }

    /**
     * Maps a String to a valid Candidate. If said Candidate does not exist in the map,
     * we create an instance of Candidate with the given name.
     * I'm not sure how this is useful if we can just add unknown candidates
     * @param name name of the candidate you want
     * @return Candidate reference from the map or a newly created reference that just got put in the map
     */
    private Candidate getCandidate(String name) {
        String normalizedName = transformCandidate(name);
        return pokemons.computeIfAbsent(normalizedName, s -> new Candidate(normalizedName));
    }

    @Override
    public Reason getReason(String candidate, String explaination) {
        // find the candidate in our private map, then find the reason with that candidate's map
        Candidate cand = getCandidate(candidate);
        String normalizedReason = transformReason(explaination);

        // if that reason does not exist, add a new instance into the candidate's map
        // not sure why we wouldn't pass the explaination as the new instance's explaination /shrug
        return cand.getReasons().computeIfAbsent(normalizedReason, Reason::new);
    }

    @Override
    public List<Reason> getReasonsForCandidate(String candidate) {
        List<Reason> reasons = new ArrayList<>( getCandidate(candidate).getReasons().values() );
        reasons.sort(Reason::compareTo);
        return reasons;
    }

    @Override
    public List<String> getMatchingReasonExplainationsForCandidate(String candidate, String prefix) {
        String normalizedPrefix = transformReason(prefix).toLowerCase();
        /*
         * for some cursed reason the book wants me to do everything in one line:
         *  - get the candidate's reasons, which are in a map
         *  - get the keys of the map (all the Strings), aka the explainations
         *  - process the keys in a stream
         *  - filter the names according to the prefix
         *  - sort the resulting stream in lexicographical order and turn them into a list
         */
        return getCandidate(candidate).getReasons().keySet().stream().map(this::transformReason)
               .filter(name -> name.toLowerCase().startsWith(normalizedPrefix))
               .sorted(Comparator.comparing(Function.identity()))
               .collect(Collectors.toList()); 
        // dear book, im sure there's a better way to write this return statement
        // note: the returned list will have duplicates eliminated thanks to keySet() returning a set
    }

    @Override
    public List<String> getMatchingCandidateNames(String prefix) {
        String normalizedPrefix = transformCandidate(prefix).toLowerCase();
        return pokemons.keySet().stream().filter(name -> name.toLowerCase().startsWith(normalizedPrefix))
               .sorted(Comparator.comparing(Function.identity()))
               .collect(Collectors.toList());
        // does a similar thing to the return statement in matching-reason-names
    }

    @Override
    public Reason voteForReason(String candidate, String explaination) {
        Reason reason = getReason(candidate, explaination);
        reason.setVotes(reason.getVotes() + 1);  // why don't we just make function for incrementing by 1? idk
        return reason;
    }
}
