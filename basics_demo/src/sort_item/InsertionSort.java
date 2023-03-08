package sort_item;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author Pumpkin
 * @createTime 2023/2/6 14:40
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        insertion_sort(arr);
    }

    private static void insertion_sort(int[] arr) {
        if (arr.length >= 2) {
            for (int i = 1; i < arr.length; i++) {
                // 挖出等会要插入的值，留下一个坑
                int x = arr[i];
                int j = i - 1;
                // 将值比x小的元素往后移动，留出一个坑
                while (j >= 0 && arr[j] < x) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                // 将挖出的值插入留出的坑中
                arr[j + 1] = x;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
