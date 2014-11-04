import java.util.concurrent.*;
import java.util.*;

public class MergeSortThread implements Callable {

	private ExecutorService exec;
	private int[] sorting;
	private int[] tempStorage;
	private int start;
	private int end;
	Object parent;

	public MergeSortThread(ExecutorService e, int[] unsorted, int[] temp, int first, int last, Object theParent) {
		exec = e;
		sorting = unsorted;
		tempStorage = temp;
		start = first;
		end = last;
		parent = theParent;
	}

	public Object call() throws Exception {
		int size = end - start;
		// System.out.println("Running Thread");
		if (size > 1) {
			int middle = start + ((end - start) + 1) / 2; 
			ArrayList<MergeSortThread> children = new ArrayList<MergeSortThread>();
			children.add(new MergeSortThread(exec, sorting, tempStorage, start, middle, this));
			children.add(new MergeSortThread(exec, sorting, tempStorage, middle, end, this));
			List<Future> finish = new ArrayList<Future>();
			finish.add(exec.submit(children.get(0)));
			finish.add(exec.submit(children.get(1)));
			finish.get(0).get();
			finish.get(1).get();

			//sort(start, middle);
			//exec.execute(children[1]);
			//sort(middle, end);
			// synchronized (children[0]) {
			// 	try {
			// 		children[0].wait();
			// 	} catch (InterruptedException e) {}
			// }
			// synchronized (children[1]) {
			// 	try {
			// 		children[1].wait();
			// 	} catch (InterruptedException e) {}
			
			// }
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
		return null;
		// synchronized (this) {
		// 	notify();
		// }
	}

}