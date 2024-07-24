package com.farms4life2016.chapter03;

import java.util.List;

import com.farms4life2016.chapter03.model.Reason;

/**
 * apparently this is an API.
 */
public interface WaifumonService {

    // this is gonna get real messy as I try to adapt the example in the book 
    // to a more personalized example that i might find funny

    /**
     * Returns a list of all the reasons why people think that
     * the specified candidate is a waifu. List will be ordered
     * by popularity based on votes, lexicographically if vote counts
     * are equal.
     * @param candidate the pokemon name
     * @return list of reasons
     */
    List<Reason> getReasonsForCandidate(String candidate);

    /**
     * Return a list containing
     * all reasons for that candidate that start with the prefix.
     * This would be more useful we returned all reasons that contained
     * the "prefix" but oh well
     * @param candidate pokemon name
     * @param prefix start of string that we will search for
     * @return a list containing all reasons for that candidate that start with prefix
     */
    List<String> getMatchingReasonExplainationsForCandidate(String candidate, String prefix);

    /**
     * Returns a list containing all candidates that start with the prefix.
     * @param prefix start of string
     * @return list of all candidates that start with prefix
     */
    List<String> getMatchingCandidateNames(String prefix);
    // why does book not use treeset instead of list? idk

    /**
     * Gets a specific reason why the community things the specified pokemon 
     * makes a good waifu
     * @param candidate name of the pokemon
     * @param explaination explaination of the reason
     * @return
     */
    Reason getReason(String candidate, String explaination);

    /**
     * increments the vote counter of the specified candidate's specified reason
     * @param candidate pokemon name
     * @param explaination which reason you want to vote for
     * @return returns the Reason object that was just modified 
     */
    Reason voteForReason(String candidate, String explaination);

}
