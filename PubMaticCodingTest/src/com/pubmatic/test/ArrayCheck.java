package com.pubmatic.test;

public class ArrayCheck {

    public static Integer getMissingValue(int input1[], int input2[]) {
        Integer missingValue = null;
        if (input1 == null || input1.length == 0 || (input1 != null && input2 != null && input1.length <= input2.length)) {
            return missingValue;
        }

        int sum = 0;

        for (int i = 0; i < input1.length; i++) {
            sum += input1[i];
        }

        if (input2 != null) {
            for (int i = 0; i < input2.length; i++) {
                sum -= input2[i];
            }
        }

        missingValue = sum;

        return missingValue;

    }

    public static void main(String[] args) {
        System.out.println(ArrayCheck.getMissingValue(new int[] {0, 2, 9, 6, 8, 7, 5, 3 }, new int[] { 6, 4, 7, 2, 1, 0, 8, 3, 9 }));
    }

}
