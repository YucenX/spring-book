package com.farms4life2016.chapter03.mem03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.farms4life2016.chapter03.AbstractWaifumonService;
import com.farms4life2016.chapter03.Normalizer;

@Component
public class WaifumonService3 extends AbstractWaifumonService {

    /*
     * @Qualifier allows you to specify the name of the component you want injected.
     * notice how we have two valid Normalizers that could have been injected in the event
     * that we did not specify qualifiers
     */
    @Autowired
    @Qualifier("second")  
    Normalizer reasonNormalizer;  

    @Autowired
    @Qualifier("cappy")
    Normalizer candidateNormalizer;

    /*
     * using our custom normalizers
     */

    @Override
    protected String transformCandidate(String input) {
        return candidateNormalizer.transform(input);
    }

    @Override
    protected String transformReason(String input) {
        return reasonNormalizer.transform(input);
    }

    /*
     * not sure why we need to expose these
     */
    
    public Normalizer getReasonNormalizer() {
        return reasonNormalizer;
    }

    public void setReasonNormalizer(Normalizer reasonNormalizer) {
        this.reasonNormalizer = reasonNormalizer;
    }

    public Normalizer getCandidateNormalizer() {
        return candidateNormalizer;
    }

    public void setCandidateNormalizer(Normalizer candidateNormalizer) {
        this.candidateNormalizer = candidateNormalizer;
    }
    
}
