package com.briskjie.cxx;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
@RunWith(AndroidJUnit4.class)
public class MyTest {
    @Test
    public void test() {
        int[] arr={6,8,4,9,3,5,1,7,2,0};
        System.out.println(Arrays.toString(heapSort(arr)));
    }

    public int[] heapSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        for (int i = 0; i < arr.length; i++) {
            buildMaxHeap(arr, arr.length - i - 1);
            swap(arr, 0, arr.length - 1 - i);
        }
        return arr;
    }

    public void buildMaxHeap(int[] arr, int maxIndex) {
        for (int i = maxIndex >>> 1; i >= 0; i--) {
            int k = i;
            while (2 * k + 1 <= maxIndex) {
                int maxValueIndex = 2 * k + 1;//将k结点的左结点设为最大值
                //说明k结点有两个子节点,2k+1还小于最大的maxIndex，那么k节点一定有两个子节点
                if (2 * k + 1 < maxIndex) {
                    if (arr[2 * k + 1] < arr[2 * k + 2]) {
                        maxValueIndex++;
                    }
                }

                if(arr[k]<arr[maxValueIndex]){
                    swap(arr,k,maxValueIndex);
                    k=maxValueIndex;
                }else
                    break;//没有发生交换，跳出while循环，再次进入for循环，查看下一个数
            }
        }
    }

    public int[] swap(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
        return arr;
    }

    @Test
    public void test2() {
        System.out.println("cxx:");
    }
}
