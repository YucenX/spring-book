package com.farms4life2016.chapter07;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * now we can actually say hello!
 * this is similar to the GreetingController from ch. 6, but
 * we also coded a few special cases (easter eggs?)
 */
@RestController
public class GreetingController {

    // this annotation allows you to handle POST and GET requests the same way
    @RequestMapping(value = {"/greeting/{name}", "/greeting/"})
    Greeting greeting(@PathVariable(required = false) String name) {
        // we have a non-necessary param in case the user doesn't provide one

        // horrible use of the tenary operator, literally unsightreadable
        String object = (name != null) ? name : "world";
        // basically sets the name to "world" if no name is provided

        // special cases
        if (object.equalsIgnoreCase("farms4life2016")) {
            return new Greeting("Welcome back, " + object + "!");
        
        // base cases
        } else {
            return new Greeting("Hello, " + object + "!");
        }
    }
}