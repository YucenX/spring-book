package com.farms4life2016.chapter06;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.farms4life2016.chapter03.WaifumonService;
import com.farms4life2016.chapter03.model.Reason;

@Controller
public class GetReasonsController {
    
    // @Autowired
    WaifumonService service;

    GetReasonsController(WaifumonService service) {
        this.service = service;
    }

    @GetMapping("/candidates/{candidate}/reasons/{explaination}")
    public ResponseEntity<Reason> getReason(
        // these annotations tell the program to extract the params out of the URI
        @PathVariable("candidate") final String candidate,
        @PathVariable("explaination") final String explaination
    ) {

        // decode the URIs (e.g. turn %20 into space characters)
        var candDecoded = URLDecoder.decode(candidate, StandardCharsets.UTF_8);
        var explDecoded = URLDecoder.decode(explaination, StandardCharsets.UTF_8);
        var reason = service.getReason(candDecoded, explDecoded);

        return new ResponseEntity<>(reason, HttpStatus.OK);
    }

    @GetMapping("/reasons")
    public ResponseEntity<List<Reason>> getReasonsByCandidate(
        // request the URI to have a parameter (e.g.  localhost/.../reasons?candidate=Braixen   )
        @RequestParam(name = "candidate") final String candidate
    ) {
        var data = service.getReasonsForCandidate(candidate);

        return new ResponseEntity<>(data, HttpStatus.OK);
        
    }
}

// had to copy code from here: https://github.com/Apress/Beginning-Spring-6-2nd-ed./blob/main/chapter06/src/main/java/com/bsg6/chapter06/GetSongsController.java 
