package com.gdut.bytedance_interview;

/**
 * @author Li Xuyang
 * date  2019/7/30 15:41
 * 快速排序
 * 面试官有知道写，因为不清晰原理，还是没写出来
 */
public class quickSort {


    //选择数据集中的最后一个对象作为基准
    private static void sort(int[] values, int low, int high) {


        if (low < high) {
            int index = partition(values, low, high);

            sort(values, low, index - 1);
            sort(values, index + 1, high);
        }


    }


    private static int partition(int[] values, int low, int high) {
        int pivot = values[high];

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (values[j] <= pivot) {
                i++;

                int temp = values[i];
                values[i] = values[j];
                values[j] = temp;

            }
        }

        i++;
        int temp = values[i];
        values[i] = values[high];
        values[high] = temp;
        return i;
    }


    public static void main(String[] args) {

        int[] values = {4, 89, 5, 3, 8, 81, 1, 45, 18, 54, 7, 6, 17, 9, 41, 65, 25};
        System.out.print("排序前: ");
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }

        //排序
        sort(values, 0, values.length - 1);

        System.out.println();
        System.out.print("排序后: ");
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }


    }
}
