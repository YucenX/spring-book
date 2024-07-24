package com.farms4life2016.chapter03;

import java.util.List;
import java.util.function.Consumer;

import com.farms4life2016.chapter03.model.Reason;

import static org.testng.Assert.assertEquals;

/**
 * this class provides some basic methods and sample data to test 
 * our API.
 */
public class WaifumonServicesTests {
    
    // sample test data
    private Object[][] model = new Object[][]{
        // candidate name,    reason   ,   vote count
        {"Gardevoir", "The Embrace Pokemon", 9},
        {"Braixen", "Field Egg Group", 3},
        {"Lucario", "Furry", 10},
        {"Lopunny", "Feminine appearance", 4},
        {"Lucario", "Unisex appearance", 11}
    };

    /**
     * allows us to execute a simple function on each row in the Object[][] model
     * @param consumer a lambda that can process the outer Object[] of model
     */
    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    /**
     * Essentially inserts the data from model into the provided service. 
     * E.g. at the time of writing, the resulting service will contain
     * an entry for Gardevoir with 9 votes, reason:"The Embrace Pokemon"
     * @param service the data from model will be inserted into this service
     */
    void populateService(WaifumonService service) {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                service.voteForReason((String) data[0], (String) data[1]);
            }
        });
    }

    /**
     * resets the service if possible. else, throws an exception
     * @param service the service to reset
     */
    void reset(WaifumonService service) {
        if (service instanceof Resettable) {
            ((Resettable)service).reset();
        } else {
            throw new RuntimeException(service + " does not implement Resettable!");
        }
    }

    void testReasonVoting(WaifumonService service) {
        reset(service);
        populateService(service);
        // ensures that the votes are inserted correctly
        iterateOverModel(data -> {
            assertEquals(service.getReason((String) data[0], (String) data[1]).getVotes(), ((Integer) data[2]).intValue());
        });
    }

    void testReasonsForCandidate(WaifumonService service) {
        reset(service);
        populateService(service);
        // tests that we can retrieve the correct reasons and vote counts for Lucario
        List<Reason> reasons = service.getReasonsForCandidate("Lucario");
        assertEquals(reasons.size(), 2);
        assertEquals(reasons.get(0).getExplaination(), "Unisex appearance");
        assertEquals(reasons.get(0).getVotes(), 11);
        assertEquals(reasons.get(1).getExplaination(), "Furry");
        assertEquals(reasons.get(1).getVotes(), 10);
        
    }

    void testMatchingCandidateNames(WaifumonService service) {
        reset(service);
        populateService(service);
        // tests that using the prefix "L" will return Lopunny and Lucario
        List<String> candidates = service.getMatchingCandidateNames("L");
        assertEquals(candidates.size(), 2); 
        assertEquals(candidates.get(0), "Lopunny");
        assertEquals(candidates.get(1), "Lucario");
    }

    void testMatchingReasonExplainationsForCandidate(WaifumonService service) {
        reset(service);
        populateService(service);

        // tests that using the prefix "F" for Lucario will return "Furry"
        List<String> explainations = service.getMatchingReasonExplainationsForCandidate("Lucario", "F");
        assertEquals(explainations.size(), 1);
        assertEquals(explainations.get(0), "Furry");
    }

}
