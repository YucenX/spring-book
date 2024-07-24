package com.farms4life2016.chapter03;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.farms4life2016.chapter03.mem03.CappyNormalizer;

public class TestCappyNormalizer {
    
    Normalizer normalizer = new CappyNormalizer();

    @DataProvider
    Object[][] data() {
        return new Object[][] {
            { "    IN   THIS   WORLD,  IT    IS KILL  or BE KILLED!      ", "In This World, It Is Kill Or Be Killed!"},
            {"Germany", "Germany"},
            {"A dog             2 3  4 lmao   ", "A Dog 2 3 4 Lmao"}
        };
    }
    @Test(dataProvider = "data")
    public void testCappyNormalization(String input, String expected) {
        assertEquals(normalizer.transform(input), expected);
    }
}
