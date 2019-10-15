/**
 * Simple class to demonstrate the quick and effiicent binary search algorithm.
 *  Only for integers at the moment.
 */
public class BinarySearch {
    public static int search(int[] array, int value) {
        int low = 0;
        int high = array.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                return array[mid];
            }
        }
        System.out.println("Item not found in array.");
        return -1;
    }
}