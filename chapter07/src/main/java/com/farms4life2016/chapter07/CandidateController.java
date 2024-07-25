package com.farms4life2016.chapter07;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {
    
    private CandidateRepository service;

    public CandidateController(CandidateRepository service) {
        this.service = service;
    }

    @GetMapping("/candidate/{id}")
    Candidate findCandidateById(@PathVariable int id) throws SQLException {
        return service.findCandidateById(id);
    }

    @GetMapping({"/candidate/search/{name}", "/candidate/search/"}) 
    Candidate findCandidateByName(@PathVariable(required = false) String name) throws SQLException {
        if (name != null) {
            return service.findCandidateByName(name);
        } else {
            throw new IllegalArgumentException("No candidate name submitted");
        }
    }

    @PostMapping("/candidate")  // note the posting and the param type
    Candidate saveCandidate(@RequestBody Candidate candidate) throws SQLException {
        return service.saveCandidate(candidate.getC_name());
    }

    @GetMapping({"/candidate/match/{name}", "/candidate/match/"})
    List<Candidate> findAllCandidatesByName( @PathVariable(required = false) String name) throws SQLException {
        return service.findAllCandidatesByName((name == null) ? "" : name);
    }

    /**
     * a very simple and uninformative error handler that just converts our custom exception
     * into a problem detail instance (presumably displayable on the web)
     * @param e
     * @return
     */
    @ExceptionHandler(CandidateNotFoundException.class) 
    ProblemDetail handleCandidateNotFound(CandidateNotFoundException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        pd.setTitle("Candidate Not Found");
        return pd;
    }

}
