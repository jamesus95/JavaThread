import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ThreadedMergeSort {

	private final Lock lock = new ReentrantLock();
	private final Condition ready = lock.newCondition();

	private ExecutorService exec;
	private int[] temp;

	public ThreadedMergeSort() {
		exec = Executors.newCachedThreadPool();
		temp = null;
	}

	public void sort(int[] unsorted) {
		temp = new int[unsorted.length];
		MergeSortThread first = new MergeSortThread(exec, unsorted, temp, 0, unsorted.length, ready, lock);
		exec.submit(first);
		lock.lock();
		try {
			ready.await();
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
		exec.shutdown();
	}

}