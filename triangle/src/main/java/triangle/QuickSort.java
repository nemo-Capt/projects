package triangle;

public class QuickSort {

    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int begin, int end) {
        if (end - begin > 0) {
            int p = partition(array, begin, end);
            quickSort(array, begin, p);
            quickSort(array, p + 1, end);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int getPivot(int begin, int end) {
        return (begin + end) / 2;
    }

    private int partition(int[] array, int begin, int end) {
        swap(array, begin, getPivot(begin, end));
        int border = begin + 1;
        for (int i = border; i <= end; i++) {
            if (array[i] < array[begin]) {
                swap(array, i, border++);
            }
        }
        swap(array, border - 1, begin);
        return border - 1;
    }
}

