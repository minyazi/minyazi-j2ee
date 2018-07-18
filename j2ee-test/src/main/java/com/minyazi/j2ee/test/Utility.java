package com.minyazi.j2ee.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utility {
    /**
     * 查找整形二维数组中最大值第一次出现的位置
     * 
     * @param numArray 整形二维数组
     * @return 整形二维数组中最大值第一次出现的位置
     */
    public static int[] findMaxElementPosition(int[][] numArray) {
        boolean isNull = true;
        int[][] maxElements = null;
        if (numArray != null && numArray.length != 0) {
            maxElements = new int[numArray.length][3];
            for (int i = 0; i < numArray.length; i++) {
                for (int j = 0; j < numArray[i].length; j++) {
                    if (isNull) {
                        isNull = false;
                    }
                    if (j == 0) {
                        maxElements[i] = new int[]{numArray[i][0], i, 0};
                    } else {
                        if (numArray[i][j] > maxElements[i][0]) {
                            maxElements[i][0] = numArray[i][j];
                            maxElements[i][2] = j;
                        }
                    }
                }
            }
        }
        
        if (isNull) {
            throw new RuntimeException("数据不能为空");
        }
        
        int[] maxElement = new int[2];
        for (int i = 0; i < maxElements.length; i++) {
            if (i == 0) {
                maxElement[0] = maxElements[0][1];
                maxElement[1] = maxElements[0][2];
            } else {
                if (maxElements[i][0] > maxElements[i-1][0]) {
                    maxElement[0] = maxElements[i][1];
                    maxElement[1] = maxElements[i][2];
                }
            }
        }
        return maxElement;
    }
    
    /**
     * 求N*N整形二维数组的全部对角线元素之和
     * 
     * @param numArray N*N整形二维数组
     * @return 全部对角线元素之和
     */
    public static int calculateSum(int[][] numArray) {
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < numArray.length; i++) {
            numbers.add(numArray[i][i]);
            if (i != numArray.length - 1 - i) {
                numbers.add(numArray[i][numArray.length - 1 - i]);
            }
        }
        System.out.println("对角线元素：" + numbers.toString());
        
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }
    
    /**
     * 去除数组中的重复项
     * 
     * @param numArray 需要去重的数组
     * @return 去重之后的数组
     */
    public static int[] removeDuplicateItem(int[] numArray) {
        Set<Integer> numbers = new HashSet<Integer>();
        for (int number : numArray) {
            numbers.add(number);
        }
        int[] result = new int[numbers.size()];
        int i = 0;
        for (int number : numbers) {
            result[i] = number;
            i++;
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[][] numArray = new int[][]{{1, 3, 4, 5}, {2, 3, 7, 3}, {4, 2, 7, 6}};
        System.out.println(Arrays.toString(Utility.findMaxElementPosition(numArray)));
        System.out.println(Utility.calculateSum(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}}));
        System.out.println(Arrays.toString(Utility.removeDuplicateItem(new int[]{1, 2, 3, 4, 5, 1, 2, 3})));
    }
}
