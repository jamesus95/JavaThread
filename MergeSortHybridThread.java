import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class MergeSortHybridThread implements Runnable {

	private final Lock lock = new ReentrantLock();
	private final Condition ready = lock.newCondition();

	private ExecutorService exec;
	private int[] sorting;
	private int[] tempStorage;
	private int start;
	private int end;
	private Condition r;
	private Lock parent;
	private boolean done;
	private int level;

	public MergeSortHybridThread(ExecutorService e, int[] unsorted, int[] temp, int first, int last, Condition con, Lock l, int num) {
		exec = e;
		sorting = unsorted;
		tempStorage = temp;
		start = first;
		end = last;
		r = con;
		parent = l;
		done = false;
		level = num;
	}

	public void run() {
		if (level < 3) {
			//System.out.println(level);
			runThread();
		} else {
			runLinear();
		}
		done = true;
		parent.lock();
		r.signalAll();
		parent.unlock();
	}

	public boolean getDone() {return done;}

	private void runThread() {
		int size = end - start;
		// System.out.println("Running Thread");
		if (size > 1) {
			int middle = start + ((end - start) + 1) / 2; 
			MergeSortHybridThread a = new MergeSortHybridThread(exec, sorting, tempStorage, start, middle, ready, lock, level + 1);
			MergeSortHybridThread b = new MergeSortHybridThread(exec, sorting, tempStorage, middle, end, ready, lock, level + 1);
			exec.submit(a);
			exec.submit(b);
			lock.lock();
			try {
				while (a.getDone() == false || b.getDone() == false) {
					ready.await();
				}
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
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
		done = true;
		parent.lock();
		r.signalAll();
		parent.unlock();
	}

	private void runLinear() {
		sort(start, end);
	}

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