import java.util.concurrent.*;

public class ThreadedMergeSort {

	private ExecutorService exec;
	private int[] temp;

	public ThreadedMergeSort() {
		exec = Executors.newCachedThreadPool();
		temp = null;
	}

	public void sort(int[] unsorted) {
		temp = new int[unsorted.length];
		MergeSortThread first = new MergeSortThread(exec, unsorted, temp, 0, unsorted.length, this);
		Future last = exec.submit(first);
		try {
			last.get();
		} catch (Exception e) {}
		exec.shutdown();
		// exec.shutdown();
		// try {
		// 	exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		// } catch (InterruptedException e) {}
	}

}