package com.farms4life2016.chapter03.mem04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.farms4life2016.chapter03.AbstractWaifumonService;
import com.farms4life2016.chapter03.Normalizer;

@Component
public class WaifumonService4 extends AbstractWaifumonService {
    
    /* now our normalizers are gonna be private and FINAL
     * it prevents our fields from being reassigned to something we don't want
     * so our pointers will always point to the same object after instantiation
     */ 
    private final Normalizer reasonNormalizer; // notice how this is a final that isn't defined during declaration
    private final Normalizer candidateNormalizer;
    // use private if necessary. using final can also help the Java VM optimize your code

    /**
     * Constructor injection, im guessing that this 
     * is something like the MIL from C++ where you can
     * initialize a constant field *right before* instantiation
     * @param reasonNormalizer
     * @param candidateNormalizer
     */
    public WaifumonService4(
        @Autowired  // this is some awful-looking code, but allows Spring to inject arguments into the constructor
        @Qualifier("second")
        Normalizer reasonNormalizer,
        @Autowired
        @Qualifier("cappy")
        Normalizer candidateNormalizer
    ) {
        this.reasonNormalizer = reasonNormalizer; // cursed reassignment of final variables 
        this.candidateNormalizer = candidateNormalizer;
    }

    @Override
    protected String transformCandidate(String input) {
        return candidateNormalizer.transform(input);
    }

    @Override
    protected String transformReason(String input) {
        return reasonNormalizer.transform(input);
    }
}
