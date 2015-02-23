package com.pubmatic.junittest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pubmatic.test.ArrayCheck;
import com.pubmatic.test.Counter;
import com.pubmatic.test.PatternMatching;

public class TestClasss {

    @Test
    public void test1() {
        assertEquals((Integer) 4, ArrayCheck.getMissingValue(new int[] { 4 }, null));
    }

    @Test
    public void test2() {
        assertEquals(null, ArrayCheck.getMissingValue(null, null));
    }

    @Test
    public void test3() {
        assertEquals((Integer) 0, ArrayCheck.getMissingValue(new int[] { 0, 1, 2 }, new int[] { 1, 2 }));
    }

    @Test
    public void test4() {
        assertEquals(
                (Integer) 5,
                ArrayCheck.getMissingValue(new int[] { 4, 1, 0, 2, 9, 6, 8, 7, 5, 3 }, new int[] { 6, 4, 7, 2, 1, 0, 8,
                        3, 9 }));
    }

    @Test
    public void test5() {
        assertEquals(null, ArrayCheck.getMissingValue(new int[] { 0, 2, 9, 6, 8, 7, 5, 3 }, new int[] { 6, 4, 7, 2, 1,
                0, 8, 3, 9 }));
    }

    @Test
    public void test6() {
        assertEquals(Counter.getInstance(5), Counter.getInstance(6));
    }
    
    @Test
    public void test7() {
        assertEquals(3, PatternMatching.getMatchedPatternCount("asnmnshtadfgmnstksjdkjhasdjkaasdsadgadfgmnstsdjadaklsjdlkaadfgmnst", "adfgmnst"));
    }
    
    @Test
    public void test8() {
        assertEquals(3, PatternMatching.getMatchedPatternCount("adfgmns3t34adfgmnstadfgmnstadfgmnst", "adfgmnst"));
    }
    
    @Test
    public void test9() {
        assertEquals(4, PatternMatching.getMatchedPatternCount("adfgmnstadfgmnstadfgmnstadfgmnst", "adfgmnst"));
    }
    
    @Test
    public void test10() {
        assertEquals(0, PatternMatching.getMatchedPatternCount("wqwqw", ""));
    }
}
