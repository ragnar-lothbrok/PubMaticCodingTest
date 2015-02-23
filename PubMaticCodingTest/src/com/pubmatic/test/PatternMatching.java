package com.pubmatic.test;

public class PatternMatching {

    public static int getMatchedPatternCount(String inputPattern, String pattern) {
        int count = 0;
        if (inputPattern != null && pattern != null && pattern.length() > 1) {
            int index = inputPattern.indexOf(pattern);
            while (index != -1) {
                count++;
                inputPattern = inputPattern.substring(index+pattern.length());
                index = inputPattern.indexOf(pattern);
            }
        }
        return count;
    }
}
