

public class MergeSort {

	private int[] sorting;
	private int[] tempStorage;

	public MergeSort() {
		sorting = null;
		tempStorage = null;
	}

	public void sort(int[] unsorted) {
		sorting = unsorted;
		tempStorage = new int[sorting.length];
		sort(0, unsorted.length);
	}

	//	0 - 10
	//	0 - 5 , 5 - 10
	//	0 - 2, 2 - 5, 	5 - 7, 7 - 10
	//	0 - 1, 1 - 2,	2 - 

	private void sort(int start, int end) {
		int size = end - start;
		if (size > 1) {
			int middle = start + ((end - start) + 1) / 2; 
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
		}
	}

}