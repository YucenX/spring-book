package com.farms4life2016.chapter03.mem02;

import org.springframework.stereotype.Component;

import com.farms4life2016.chapter03.Normalizer;

/**
 * this class is just a realization of Normalizer (an abstract interface that
 * cannot be instantiated on its own). Other than that, this class does nothing.
 */
@Component
public class SimpleNormalizer implements Normalizer {
    /* inherits default transform() method from interface */
}
