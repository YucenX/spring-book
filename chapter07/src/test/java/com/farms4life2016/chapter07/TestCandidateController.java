package com.farms4life2016.chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCandidateController {

    @Autowired
    private TestRestTemplate restTemplate;

    @DataProvider
    Object[][] candidateData() {
        return new Object[][]{
            // valid id-name pairs (should return status 200)
            {1, "Gardevoir"},  // not sure why the book wants us to make new Object[] every time
            {2, "Lucario"},
            {3, "Lopunny"},
            {4, "Braixen"},

            // invalid id-name pairs (should return status 404)
            {-1, null},
            {-9, "invalid candidate"}
        };
    }

    @Test(dataProvider = "candidateData")
    public void testGetCandidate(int id, String name) {
        // checks that /candidate/{id} works as intended
        String url = "/candidate/" + id;
        ResponseEntity<Candidate> response = restTemplate.getForEntity(url, Candidate.class);
        // split test cases into valid data and invalid data
        if (id >= 0) {
            assertEquals(response.getStatusCode(), HttpStatus.OK);
            Candidate candidate = response.getBody();
            Candidate data = new Candidate(id, name);
            assertEquals(candidate, data);
        } else {
            assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    // the rest of this test is very long and idk if i want to finish it...
    
}
