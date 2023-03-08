package sort_item;

import java.util.Arrays;

/**
 * 选择排序
 * @author Pumpkin
 * @createTime 2023/2/6 13:50
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        selection_sort(arr);
    }
    private static void selection_sort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int max = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            if (max != i) {
                int temp = arr[max];
                arr[max] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
