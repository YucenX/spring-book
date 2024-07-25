package com.farms4life2016.chapter06;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    // this annotation maps this controller to the GET request?
    @GetMapping(
        path = "/greeting",
        produces = {MediaType.TEXT_PLAIN_VALUE} // produces plain text
    )
    
    public ResponseEntity<String> greeting() {
        // string to be turned into plain text
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
    }

}
