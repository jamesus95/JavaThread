import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class MergeSortHybrid {

	private final Lock lock = new ReentrantLock();
	private final Condition ready = lock.newCondition();

	private ExecutorService exec;
	private int[] temp;

	public MergeSortHybrid() {
		exec = Executors.newCachedThreadPool();
		temp = null;
	}

	public void sort(int[] unsorted) {
		temp = new int[unsorted.length];
		MergeSortHybridThread first = new MergeSortHybridThread(exec, unsorted, temp, 0, unsorted.length, ready, lock, 0);
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