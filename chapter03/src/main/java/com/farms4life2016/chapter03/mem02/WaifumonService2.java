package com.farms4life2016.chapter03.mem02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farms4life2016.chapter03.AbstractWaifumonService;
import com.farms4life2016.chapter03.Normalizer;

/**
 * our second class that overrides AbstractWaifumonService and 
 * provides some more functionality
 */
@Component
public class WaifumonService2 extends AbstractWaifumonService {

    @Autowired
    Normalizer normalizer;

    @Override
    protected String transformCandidate(String input) {
        return normalizer.transform(input);
    }

    @Override
    protected String transformReason(String input) {
        return normalizer.transform(input);
    }
    
}
