package sort_item;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Pumpkin
 * @createTime 2023/2/6 13:28
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        bubble_sort(arr);
    }

    private static void bubble_sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
