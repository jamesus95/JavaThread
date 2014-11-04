import java.util.concurrent.*;

public class MergeSortThread implements Runnable {

	private ExecutorService exec;
	private int[] sorting;
	private int start;
	private int end;

	public MergeSortThread(ExecutorService e, int[] unsorted, int first, int last) {
		exec = e;
		sorting = sorted;
		start = first;
		end = last;
	}

	public void run() {
		int size = end - start;
		if (size > 1) {
			int middle = start + ((end - start) + 1) / 2; 
			int size = end - start;
		if (size > 1) {
			int middle = start + ((end - start) + 1) / 2; 
			exec.
			sort(start, middle);
			sort(middle, end);
			int left = start;
			int right = middle;
			int index = 0;
			while (left < middle && right < end) {
				if (sorting[left] < sorting[right]) {
					tempStorage[index++] = sorting[left++];
				} else {
					tempStorage[index++] = sorting[right++];
				}
			}
			while (left < middle) {
				tempStorage[index++] = sorting[left++];
			}
			while (right < end) {
				tempStorage[index++] = sorting[right++];
			}
			int tempIndex = start;
			index = 0;
			while (tempIndex < end) {
				sorting[tempIndex++] = tempStorage[index++];
			}
			int left = start;
			int right = middle;
			int index = 0;
			while (left < middle && right < end) {
				if (sorting[left] < sorting[right]) {
					tempStorage[index++] = sorting[left++];
				} else {
					tempStorage[index++] = sorting[right++];
				}
			}
			while (left < middle) {
				tempStorage[index++] = sorting[left++];
			}
			while (right < end) {
				tempStorage[index++] = sorting[right++];
			}
			int tempIndex = start;
			index = 0;
			while (tempIndex < end) {
				sorting[tempIndex++] = tempStorage[index++];
			}
	}

}